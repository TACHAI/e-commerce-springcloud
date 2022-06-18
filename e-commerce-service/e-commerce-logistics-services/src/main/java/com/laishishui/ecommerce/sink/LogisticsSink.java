package com.laishishui.ecommerce.sink;

/**
 * @author tachai on 2022/6/18 3:38 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * <h1>自定义物流信息接收器（Sink）</h1>
 */
public interface LogisticsSink {
    /**
     * 输入信道名称
     */
    String INPUT ="logisticsSink";

    /**
     * <h2>物流Sink ->logisticsInput</h2>
     * @return
     */
    @Input(LogisticsSink.INPUT)
    SubscribableChannel logisticsInput();
}
