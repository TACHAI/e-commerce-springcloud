package com.laishishui.ecommerce.service;

/**
 * @author tachai on 2022/6/18 3:40 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.LogisticsApplication;
import com.laishishui.ecommerce.dao.EcommerceLogisticsDao;
import com.laishishui.ecommerce.entity.EcommerceLogistics;
import com.laishishui.ecommerce.order.LogisticsMessage;
import com.laishishui.ecommerce.sink.LogisticsSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * <h1>物流服务实现</h1>
 */
@Slf4j
@EnableBinding(LogisticsSink.class)
public class LogisticsServiceImpl {

    private final EcommerceLogisticsDao logisticsDao;

    public LogisticsServiceImpl(EcommerceLogisticsDao logisticsDao){
        this.logisticsDao= logisticsDao;
    }

    /**
     * <h2>订阅监听订单微服务发送的物流消息</h2>
     * @param payLoad
     */
    @StreamListener("logisticsInput")
    public void consumeLogisticsMessage(@Payload Object payLoad){
        log.info("received and consume logistics message:[{}]",payLoad.toString());
        LogisticsMessage logisticsMessage = JSON.parseObject(
                payLoad.toString(), LogisticsMessage.class
        );
        EcommerceLogistics ecommerceLogistics= logisticsDao.save(
                new EcommerceLogistics(
                        logisticsMessage.getUserId(),
                        logisticsMessage.getOrderId(),
                        logisticsMessage.getAddressId(),
                        logisticsMessage.getExtraInfo()
                )
        );
        log.info("consume logistics message success: [{}]",ecommerceLogistics.getId());
    }
}
