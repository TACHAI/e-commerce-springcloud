package com.laishishui.ecommerce.stream.qinyi;

/**
 * @author tachai on 2022/6/12 8:32 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.vo.LaishishuiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * <h1>使用自定义的输入信道实现消息的接收</h1>
 * */
@Slf4j
@EnableBinding(QinyiSink.class)
public class QinyiReceiveService {

    /** 使用自定义的输入信道接收消息 */
    @StreamListener(QinyiSink.INPUT)
    public void receiveMessage(@Payload Object payload) {

        log.info("in QinyiReceiveService consume message start");
        LaishishuiMessage qinyiMessage = JSON.parseObject(payload.toString(), LaishishuiMessage.class);
        log.info("in QinyiReceiveService consume message success: [{}]",
                JSON.toJSONString(qinyiMessage));
    }
}
