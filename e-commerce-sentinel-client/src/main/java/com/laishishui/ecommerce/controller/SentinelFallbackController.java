package com.laishishui.ecommerce.controller;

/**
 * @author tachai on 2022/6/18 6:59 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.block_handler.LaishishuiBlockHandler;
import com.laishishui.ecommerce.vo.JwtToken;
import com.laishishui.ecommerce.vo.UsernameAndPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>Sentinel 提供容错降级的功能</h1>
 * */
@SuppressWarnings("all")
@Slf4j
@RestController
@RequestMapping("/sentinel-fallback")
public class SentinelFallbackController {

    /** 注入没有增强的 RestTemplate */
    private final RestTemplate restTemplate;

    public SentinelFallbackController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/get-token")
    @SentinelResource(
            value = "getTokenFromAuthorityService",
            fallback = "getTokenFromAuthorityServiceFallback",
            fallbackClass = { LaishishuiBlockHandler.class }
    )
    public JwtToken getTokenFromAuthorityService(
            @RequestBody UsernameAndPassword usernameAndPassword) {

        String requestUrl =
                "http://127.0.0.1:7000/ecommerce-authority-center/authority/token";
        log.info("RestTemplate request url and body: [{}], [{}]",
                requestUrl, JSON.toJSONString(usernameAndPassword));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(usernameAndPassword), headers),
                JwtToken.class
        );
    }

    /**
     * <h2>让 Sentinel 忽略一些异常</h2>
     * */
    @GetMapping("/ignore-exception")
    @SentinelResource(
            value = "ignoreException",
            fallback = "ignoreExceptionFallback",
            fallbackClass = { LaishishuiBlockHandler.class },
            exceptionsToIgnore = { NullPointerException.class }
    )
    public JwtToken ignoreException(@RequestParam Integer code) {

        if (code % 2 == 0) {
            throw new NullPointerException("yout input code is: " + code);
        }

        return new JwtToken("qinyi-imooc");
    }
}
