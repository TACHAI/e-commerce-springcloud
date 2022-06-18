package com.laishishui.ecommerce.feign;

import com.laishishui.ecommerce.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tachai on 2022/6/18 6:55 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
/**
 * <h1>Sentinel 对 OpenFeign 接口的降级策略</h1>
 * */
@Slf4j
@Component
public class SentinelFeignClientFallback implements SentinelFeignClient{
    @Override
    public CommonResponse<String> getResultByFeign(Integer code) {

        log.error("request supply for test has some error: [{}]", code);
        return new CommonResponse<>(
                -1,
                "sentinel feign fallback",
                "input code: "+ code
        );
    }
}
