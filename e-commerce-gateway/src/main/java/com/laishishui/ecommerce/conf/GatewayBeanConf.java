package com.laishishui.ecommerce.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>网关需要注入到容器中的Bean</h1>
 * @author tachai on 2022/1/2 8:02 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Configuration
public class GatewayBeanConf {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
