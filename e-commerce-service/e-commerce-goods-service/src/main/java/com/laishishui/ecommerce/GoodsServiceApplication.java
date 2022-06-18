package com.laishishui.ecommerce;

import com.laishishui.ecommerce.conf.DataSourceProxyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * <h1>商品微服务启动入口</h1>
 * 启动依赖组件/中间件：Redis +MySql+Nacos+Kafka+Zipkin
 * http://127.0.0.1:8001/ecommerce-goods-service/doc.html
 * @author tachai on 2022/1/9 8:36 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
@Import(DataSourceProxyAutoConfiguration.class)
public class GoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceApplication.class,args);
    }
}
