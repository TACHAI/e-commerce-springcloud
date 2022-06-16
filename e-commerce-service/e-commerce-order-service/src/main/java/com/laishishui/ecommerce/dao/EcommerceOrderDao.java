package com.laishishui.ecommerce.dao;

import com.laishishui.ecommerce.entity.EcommerceOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <h1> EcommerceOrder Dao 接口定义</h1>
 * @author tachai on 2022/6/16 7:33 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface EcommerceOrderDao extends PagingAndSortingRepository<EcommerceOrder,Long> {
    /**
     * <h2>根据userId 查询分页订单</h2>
     *  select * from t_ecommerce_order where user_id = ?
     *  order by ... desc/asc limit x offset y
     * @param userId
     * @param pageable
     * @return
     */
    Page<EcommerceOrder> findAllByUserId(Long userId, Pageable pageable);
}
