package com.laishishui.ecommerce.service.impl;

import com.laishihui.ecommerce.vo.LoginUserInfo;
import com.laishishui.ecommerce.account.BalanceInfo;
import com.laishishui.ecommerce.dao.EcommerceBalanceDao;
import com.laishishui.ecommerce.entity.EcommerceBalance;
import com.laishishui.ecommerce.filter.AccessContext;
import com.laishishui.ecommerce.service.IBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tachai on 2022/1/8 2:22 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BalanceServiceImpl implements IBalanceService {

    private final EcommerceBalanceDao balanceDao;

    public BalanceServiceImpl(EcommerceBalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    @Override
    public BalanceInfo getCurrentUserBalanceInfo() {
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        BalanceInfo balanceInfo = new BalanceInfo(
                loginUserInfo.getId(),
                0L
        );
        EcommerceBalance ecommerceBalance=balanceDao.findByUserId(loginUserInfo.getId());

        if(null !=ecommerceBalance){
            balanceInfo.setBalance(ecommerceBalance.getBalance());
        }else {
            // 如果还没有用户余额记录，这里创建出来，余额设定为0
            EcommerceBalance newBalance = new EcommerceBalance();
            newBalance.setUserId(loginUserInfo.getId());
            newBalance.setBalance(0L);
            log.info("init user balance record: [{}]",
                    balanceDao.save(newBalance).getId());
        }

        return balanceInfo;
    }

    @Override
    public BalanceInfo deductBalance(BalanceInfo balanceInfo) {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 扣减用户余额的一个基本原则: 扣减额 <= 当前用户余额
        EcommerceBalance ecommerceBalance = balanceDao.findByUserId(loginUserInfo.getId());
        if(null==ecommerceBalance
                ||ecommerceBalance.getBalance()-balanceInfo.getBalance()<0){
            throw new RuntimeException("user balance is not enough");
        }

        Long sourceBalance = ecommerceBalance.getBalance();
        ecommerceBalance.setBalance(ecommerceBalance.getBalance()-balanceInfo.getBalance());

        log.info("deduct balance: [{}],[{}],[{}]",
                balanceDao.save(ecommerceBalance).getId(),
                sourceBalance,
                ecommerceBalance.getBalance());

        return new BalanceInfo(
                ecommerceBalance.getUserId(),
                ecommerceBalance.getBalance());
    }
}
