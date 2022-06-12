package com.laishishui.ecommerce.stream;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.vo.LaishishuiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;


/**
 * <h1>使用默认的通信信道实现消息的发送</h1>
 * @author tachai on 2022/6/12 8:24 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@EnableBinding(Source.class)
public class DefaultSendService {
    private final Source source;

    public DefaultSendService(Source source) {
        this.source = source;
    }

    /**
     * <h2>使用默认的输出信道发送消息</h2>
     * */
    public void sendMessage(LaishishuiMessage message) {

        String _message = JSON.toJSONString(message);
        log.info("in DefaultSendService send message: [{}]", _message);

        // Spring Messaging, 统一消息的编程模型, 是 Stream 组件的重要组成部分之一
        source.output().send(MessageBuilder.withPayload(_message).build());
    }
}
