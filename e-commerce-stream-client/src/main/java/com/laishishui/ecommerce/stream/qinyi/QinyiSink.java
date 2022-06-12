package com.laishishui.ecommerce.stream.qinyi;

/**
 * @author tachai on 2022/6/12 8:29 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * <h1>自定义输入信道</h1>
 * */
public interface QinyiSink {

    String INPUT = "qinyiInput";

    /** 输入信道的名称是 qinyiInput, 需要使用 Stream 绑定器在 yml 文件中配置*/
    @Input(QinyiSink.INPUT)
    SubscribableChannel qinyiInput();
}
