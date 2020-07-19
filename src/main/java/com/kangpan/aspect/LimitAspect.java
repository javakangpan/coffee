package com.kangpan.aspect;

import com.google.common.collect.ImmutableList;
import com.kangpan.annotation.Limit;
import com.kangpan.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 限流切面
 * @author 康盼Java开发工程师
 * @date 2020/07/19
 */
@Aspect
@Component
@Slf4j
public class LimitAspect {
    private final RedisTemplate<Object, Object> redisTemplate;

    public LimitAspect(RedisTemplate<Object, Object> redisTemplate) {
        //设置String key 和value序列化模式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("@annotation(com.kangpan.annotation.Limit)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Limit limit = method.getAnnotation(Limit.class);
        String key = limit.key();
        ImmutableList<Object> keys = ImmutableList.of(key);
        String lua = "local num"
                + "\n local key_local = redis.call('setnx',KEYS[1],0)" // KEY存在不做任何事情 不存在赋值为0
                + "\n if (tonumber(key_local) == 1)" // ==0 表示KEY存在 没有赋值  ==1 表示KEY不存在 赋值0了
                + "\n then"
                    + "\n redis.call('incr',KEYS[1])" // 存储在key中的数字值加1 如果key不存在 key的值先被设置为0 然后再执行加1操作
                    + "\n num = redis.call('get',KEYS[1])" // 得到Key的值
                        + "\n if (tonumber(num) == 1)" // 第一次赋值
                        + "\n then"
                            + "\n redis.call('expire',KEYS[1],ARGV[2])" // 设置有效时间
                        + "\n end"
                    + "\n return tonumber(num);"
                + "\n else"
                    + "\n redis.call('incr',KEYS[1])"
                    + "\n num = redis.call('get',KEYS[1])"
                    + "\n return tonumber(num);"
                + "\n end";

        RedisScript<Number> redisScript = new DefaultRedisScript<>(lua, Number.class);
        Number number = redisTemplate.execute(redisScript, keys, limit.count(), limit.period());
        if (null != number && number.intValue() <= limit.count()) {
            log.info("方法{}: 第{}次访问 有效时间为 {}", limit.key(), number, redisTemplate.getExpire(limit.key()));
            return proceedingJoinPoint.proceed();
        } else {
            throw new BadRequestException("访问次数受限制");
        }
    }
}
