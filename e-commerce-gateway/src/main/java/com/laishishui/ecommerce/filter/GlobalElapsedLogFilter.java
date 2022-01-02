package com.laishishui.ecommerce.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author tachai on 2022/1/2 7:07 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Component
public class GlobalElapsedLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //  前置逻辑
        StopWatch sw = StopWatch.createStarted();
        String uri = exchange.getRequest().getURI().getPath();
        return chain.filter(exchange).then(
                // 后置逻辑
                Mono.fromRunnable(()->
                        log.info("[{}] elapsed: [{}ms]",
                                uri,sw.getTime(TimeUnit.MILLISECONDS))
                        )
        );
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+3;
    }
}
