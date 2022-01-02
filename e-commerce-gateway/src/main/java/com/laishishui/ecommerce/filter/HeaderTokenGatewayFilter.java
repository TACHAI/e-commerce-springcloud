package com.laishishui.ecommerce.filter;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <h1> 请求头部携带 Token 验证过滤器</h1>
 * @author tachai on 2022/1/1 10:38 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class HeaderTokenGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从 HTTP Header 中寻找key 为token,value 为imooc 的键值对
        String name = exchange.getRequest().getHeaders().getFirst("token");
        if("laishishui".equals(name)){
            return chain.filter(exchange);
        }

        // 标记此次请求没有权限，并结束这次请求
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+3;
    }
}
