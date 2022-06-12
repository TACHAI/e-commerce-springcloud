package com.laishishui.ecommerce.stream.qinyi;

/**
 * @author tachai on 2022/6/12 8:30 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <h1>自定义输出信道</h1>
 * */
public interface QinyiSource {

    String OUTPUT = "qinyiOutput";

    /** 输出信道的名称是 qinyiOutput, 需要使用 Stream 绑定器在 yml 文件中声明 */
    @Output(QinyiSource.OUTPUT)
    MessageChannel qinyiOutput();
}
