package com.laishishui.ecommerce.stream.qinyi;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.vo.LaishishuiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * <h1>使用自定义的通信信道 QinyiSource 实现消息的发送</h1>
 * @author tachai on 2022/6/12 8:31 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@EnableBinding(QinyiSource.class)
public class QinyiSendService {
    private final QinyiSource qinyiSource;

    public QinyiSendService(QinyiSource qinyiSource) {
        this.qinyiSource = qinyiSource;
    }

    /**
     * <h2>使用自定义的输出信道发送消息</h2>
     * */
    public void sendMessage(LaishishuiMessage message) {

        String _message = JSON.toJSONString(message);
        log.info("in QinyiSendService send message: [{}]", _message);
        qinyiSource.qinyiOutput().send(
                MessageBuilder.withPayload(_message).build()
        );
    }
}
