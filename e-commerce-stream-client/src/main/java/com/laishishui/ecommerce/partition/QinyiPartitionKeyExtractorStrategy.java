package com.laishishui.ecommerce.partition;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.vo.LaishishuiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 *
 * <h1>自定义从 Message 中提取 partition key 的策略</h1>
 * @author tachai on 2022/6/12 8:36 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Component
public class QinyiPartitionKeyExtractorStrategy implements PartitionKeyExtractorStrategy {
    @Override
    public Object extractKey(Message<?> message) {

        LaishishuiMessage qinyiMessage = JSON.parseObject(
                message.getPayload().toString(), LaishishuiMessage.class
        );
        // 自定义提取 key
        String key = qinyiMessage.getProjectName();
        log.info("SpringCloud Stream Qinyi Partition Key: [{}]", key);
        return key;    }
}
