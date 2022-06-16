package com.laishishui.ecommerce.feign;

/**
 * @author tachai on 2022/6/16 8:10 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.laishishui.ecommerce.account.BalanceInfo;
import com.laishishui.ecommerce.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <h1>用户账户服务 Feign 接口</h1>
 * */
@FeignClient(
        contextId = "NotSecuredBalanceClient",
        value = "e-commerce-account-service"
)
public interface NoSecuredBalanceClient {
    @RequestMapping(
            value = "/ecommerce-account-service/balance/deduct-balance",
            method = RequestMethod.PUT
    )
    CommonResponse<BalanceInfo> deductBalance(@RequestBody BalanceInfo balanceInfo);
}
