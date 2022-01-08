package com.laishishui.ecommerce.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tachai on 2022/1/8 2:43 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
public class BalanceServiceTest extends BaseTest{

    @Autowired
    private IBalanceService balanceService;

    /**
     * <h2>测试获取当前用户的余额信息</h2>
     */
    @Test
    public void testGetCurrentUserBalanceInfo(){
       log.info("test get current user balance info: [{}]", JSON.toJSONString(
               balanceService.getCurrentUserBalanceInfo().getBalance()
       ));
    }
}
