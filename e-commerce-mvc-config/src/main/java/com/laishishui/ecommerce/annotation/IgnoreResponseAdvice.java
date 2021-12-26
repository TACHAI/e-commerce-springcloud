package com.laishishui.ecommerce.annotation;

/**
 * Create by tachai on 2021/12/25 9:38 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import org.w3c.dom.Element;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>忽略统一响应注解定义</h1>
 * */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}
