package com.laishishui.ecommerce.conf;

/**
 * @author tachai on 2022/6/18 6:49 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>开启服务间的调用保护, 需要给 RestTemplate 做一些包装</h1>
 * */
@Slf4j
@Configuration
public class RestTemplateExceptionUtil {

    /**
     * <h2>包装 RestTemplate</h2>
     * */
    @Bean
//    @SentinelRestTemplate(
//            fallback = "handleFallback", fallbackClass = RestTemplateExceptionUtil.class,
//            blockHandler = "handleBlock", blockHandlerClass = RestTemplateExceptionUtil.class
//    )
    public RestTemplate restTemplate() {
        return new RestTemplate();  // 可以对其做一些业务相关的配置
    }

}
