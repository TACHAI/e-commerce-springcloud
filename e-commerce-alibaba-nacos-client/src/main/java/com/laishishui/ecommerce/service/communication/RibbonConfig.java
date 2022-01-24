package com.laishishui.ecommerce.service.communication;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>使用Ribbon 之前的配置，增强RestTemplate</h1>
 * @author tachai on 2022/1/24 9:46 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Component
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
