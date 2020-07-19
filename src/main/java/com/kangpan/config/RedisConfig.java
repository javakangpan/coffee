package com.kangpan.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Configuration;
/**
 * redis配置 1.序列化和反序列化
 * @author 康盼Java开发工程师
 * @date 2020/07/19
 */

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {


}
