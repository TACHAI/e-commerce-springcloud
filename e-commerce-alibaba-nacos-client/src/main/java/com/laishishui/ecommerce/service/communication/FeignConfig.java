package com.laishishui.ecommerce.service.communication;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author tachai on 2022/1/25 8:46 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Configuration
public class FeignConfig {

    /**
     * <h2>开启 OpenFeign日志</h2>
     * @return
     */
    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.FULL;  //需要注意，日志级别需要修改成debug
    }

    /**
     * <h2>OpenFeign开始重试</h2>
     * period =100 发起当前请求的时间间隔，单位是ms
     * maxPeriod = 1000,发起当前请求的最大时间间隔，单位是ms
     * maxAttempts =5 最多请求次数
     * @return
     */
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(
                100,
                SECONDS.toMillis(1),
                5
        );
    }


    public static final int CONNECT_TIMEOUT_MILLS =5000;
    public static final int READ_TIMEOUT_MILLS =5000;

    /**
     * <h2>对请求的连接和响应时间进行限制</h2>
     * @return
     */
    public Request.Options options(){
        return new Request.Options(
                CONNECT_TIMEOUT_MILLS, TimeUnit.MICROSECONDS,
                READ_TIMEOUT_MILLS,TimeUnit.MILLISECONDS,
                true
        );
    }
}
