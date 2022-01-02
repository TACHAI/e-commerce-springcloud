package com.laishishui.ecommerce.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>配置登录请求转发规则</h1>
 * @author tachai on 2022/1/2 8:57 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Configuration
public class RouteLocatorConfig {

    /**
     * <h2> 使用代码定义路由规则，在网关层面拦截下登录和注册接口</h2>
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator loginRouteLocator(RouteLocatorBuilder builder){
        // 手动定义 Gateway 路由规则需要指定id、path 和uri
        return builder.routes()
                .route("e_commerce_authority",
                        r->r.path("/laishishui/e-commerce/login",
                                "/laishishui/e-commerce/register")
                                .uri("http://localhost:9001")).build();
    }
}
