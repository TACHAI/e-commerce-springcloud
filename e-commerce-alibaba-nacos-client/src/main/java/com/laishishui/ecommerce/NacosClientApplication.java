package com.laishishui.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Create by tachai on 2021/12/26 7:07 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

@EnableDiscoveryClient
@SpringBootApplication
public class NacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class,args);
    }
}
