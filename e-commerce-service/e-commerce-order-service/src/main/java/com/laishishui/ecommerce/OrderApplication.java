package com.laishishui.ecommerce;

import com.laishishui.ecommerce.conf.DataSourceProxyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * <h1> 订单违服务启动入口</h1>
 * @author tachai on 2022/6/16 2:43 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@EnableJpaAuditing
@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@Import(DataSourceProxyAutoConfiguration.class)
public class OrderApplication {


    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
