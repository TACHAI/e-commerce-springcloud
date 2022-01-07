package com.laishishui.ecommerce.service;

import com.laishishui.ecommerce.account.BalanceInfo;

/**
 * <h1>用于余额相关的服务接口定义</h1>
 * @author tachai on 2022/1/7 3:42 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface IBalanceService  {

    /**
     * <h2>获取当前用户余额信息</h2>
     * @return
     */
    BalanceInfo getCurrentUserBalanceInfo();

    /**
     * <h2>扣减用户余额</h2>
     * @param balanceInfo  代表想要扣减的余额
     * @return
     */
    BalanceInfo deductBalance(BalanceInfo balanceInfo);
}
