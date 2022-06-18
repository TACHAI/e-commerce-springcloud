package com.laishishui.ecommerce.feign;

/**
 * @author tachai on 2022/6/18 6:54 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.laishishui.ecommerce.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <h1>通过 Sentinel 对 OpenFeign 实现熔断降级</h1>
 * */
@FeignClient(
        value = "e-commerce-imooc",
        fallback = SentinelFeignClientFallback.class
)
public interface SentinelFeignClient {
    @RequestMapping(value = "qinyi", method = RequestMethod.GET)
    CommonResponse<String> getResultByFeign(@RequestParam Integer code);
}
