package com.laishishui.ecommerce.dao;

import com.laishishui.ecommerce.entity.EcommerceBalance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>EcommerceBalance Dao 接口定义</h1>
 * @author tachai on 2022/1/7 11:01 上午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface EcommerceBalanceDao extends JpaRepository<EcommerceBalance,Long> {
    /** 根据userId 查询EcommerceBalance 对象*/
    EcommerceBalance findByUserId(Long userId);
}
