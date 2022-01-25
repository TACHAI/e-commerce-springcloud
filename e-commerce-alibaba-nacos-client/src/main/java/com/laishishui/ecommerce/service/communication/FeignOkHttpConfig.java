package com.laishishui.ecommerce.service.communication;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <h1>不是必须的，但是通常会使用  OpenFeign使用OkHttp配置类</h1>
 * @author tachai on 2022/1/25 9:06 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignOkHttpConfig {


    /**
     * <h2>注入OkHttp,并自定义配置</h2>
     * @return
     */
    @Bean
    public okhttp3.OkHttpClient okHttpClient(){
       return new OkHttpClient.Builder()
               .connectTimeout(5, TimeUnit.SECONDS) //设置连接超时
               .readTimeout(5,TimeUnit.SECONDS) // 设置读超时
               .writeTimeout(5,TimeUnit.SECONDS) // 设置写超时
               .retryOnConnectionFailure(true)          // 是否自动重连
               .connectionPool(new ConnectionPool(      // 配置连接池中的最大空闲线程个数为10，并保持5分钟
                       10,
                       5,
                       TimeUnit.SECONDS)).build();
    }
}
