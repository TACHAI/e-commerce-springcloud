package com.laishishui.ecommerce.dao;

import com.laishishui.ecommerce.entity.EcommerceAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1>EcommerceAddress Dao 接口定义</h1>
 * @author tachai on 2022/1/6 11:01 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface EcommerceAddressDao extends JpaRepository<EcommerceAddress,Long> {

    /**
     * <h2>根据用户id 查询地址信息</h2>
     * @param userId
     * @return
     */
    List<EcommerceAddress> findAllByUserId(Long userId);
}
