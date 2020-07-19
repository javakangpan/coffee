package com.kangpan.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @date 2020/07/19
 * @author 康盼Java开发工程师
 *
 * 限流 用于方法上 运行期间
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    // 方法名为key
    String key() default "";

    // 时间的，单位秒
    int period();

    // 限制访问次数
    int count();
}
