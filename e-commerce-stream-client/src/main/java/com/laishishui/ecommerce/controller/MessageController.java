package com.laishishui.ecommerce.controller;

/**
 * @author tachai on 2022/6/12 8:38 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.laishishui.ecommerce.stream.DefaultSendService;
import com.laishishui.ecommerce.stream.qinyi.QinyiSendService;
import com.laishishui.ecommerce.vo.LaishishuiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>构建消息驱动</h1>
 * */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    private final DefaultSendService defaultSendService;
    private final QinyiSendService qinyiSendService;

    public MessageController(DefaultSendService defaultSendService,
                             QinyiSendService qinyiSendService) {
        this.defaultSendService = defaultSendService;
        this.qinyiSendService = qinyiSendService;
    }

    /**
     * <h2>默认信道</h2>
     * */
    @GetMapping("/default")
    public void defaultSend() {
        defaultSendService.sendMessage(LaishishuiMessage.defaultMessage());
    }

    /**
     * <h2>自定义信道</h2>
     * */
    @GetMapping("/qinyi")
    public void qinyiSend() {
        qinyiSendService.sendMessage(LaishishuiMessage.defaultMessage());
    }

}
