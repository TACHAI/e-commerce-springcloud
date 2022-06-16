package com.laishishui.ecommerce.service;

import com.laishishui.ecommerce.common.TableId;
import com.laishishui.ecommerce.order.OrderInfo;
import com.laishishui.ecommerce.vo.PageSimpleOrderDetail;

/**
 *  * <h1>订单相关服务接口定义</h1>
 * @author tachai on 2022/6/16 8:02 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface IOrderService {

    /**
     * <h2>下单(分布式事务): 创建订单 -> 扣减库存 -> 扣减余额 -> 创建物流信息(Stream + Kafka)</h2>
     * */
    TableId createOrder(OrderInfo orderInfo);

    /**
     * <h2>获取当前用户的订单信息: 带有分页</h2>
     * */
    PageSimpleOrderDetail getSimpleOrderDetailByPage(int page);
}
