package com.laishishui.ecommerce.stream;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.vo.LaishishuiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * <h1>使用默认的信道实现消息的接收</h1>
 * @author tachai on 2022/6/12 8:26 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

@Slf4j
@EnableBinding(Sink.class)
public class DefaultReceiveService {
    /**
     * <h2>使用默认的输入信道接收消息</h2>
     * */
    @StreamListener(Sink.INPUT)
    public void receiveMessage(Object payload) {

        log.info("in DefaultReceiveService consume message start");
        LaishishuiMessage message = JSON.parseObject(
                payload.toString(), LaishishuiMessage.class
        );
        // 消费消息
        log.info("in DefaultReceiveService consume message success: [{}]",
                JSON.toJSONString(message));
    }
}
