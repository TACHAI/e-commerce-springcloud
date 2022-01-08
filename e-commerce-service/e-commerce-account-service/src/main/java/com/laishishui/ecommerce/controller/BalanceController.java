package com.laishishui.ecommerce.controller;

import com.laishishui.ecommerce.account.BalanceInfo;
import com.laishishui.ecommerce.service.IBalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>用户余额服务</h1>
 * @author tachai on 2022/1/8 8:17 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Api(value = "用户余额服务")
@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final IBalanceService balanceService;

    public BalanceController(IBalanceService balanceService) {
        this.balanceService = balanceService;
    }


    @ApiOperation(value = "当前用户余额",notes = "获取当前用户余额",httpMethod = "GET")
    @GetMapping("/current-balance")
    public BalanceInfo getCurrentUserBalance(){
        return balanceService.getCurrentUserBalanceInfo();
    }

    @ApiOperation(value = "扣减余额",notes="获取用户余额",httpMethod = "PUT")
    @PutMapping("/deduct-balance")
    public BalanceInfo deductBalance(@RequestBody BalanceInfo balanceInfo){
        return balanceService.deductBalance(balanceInfo);
    }
}
