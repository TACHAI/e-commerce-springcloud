package com.laishishui.ecommerce;

/**
 * @author tachai on 2022/6/18 3:25 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.laishishui.ecommerce.conf.DataSourceProxyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * <h1>物流微服务启动入口</h1>
 * */
@Import(DataSourceProxyAutoConfiguration.class)
@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class LogisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogisticsApplication.class,args);
    }
}
