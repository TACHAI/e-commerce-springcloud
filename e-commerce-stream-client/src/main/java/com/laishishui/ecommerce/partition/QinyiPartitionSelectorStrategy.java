package com.laishishui.ecommerce.partition;

/**
 * @author tachai on 2022/6/12 8:34 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.stereotype.Component;

/**
 * <h1>决定 message 发送到哪个分区的策略</h1>
 * */
@Slf4j
@Component
public class QinyiPartitionSelectorStrategy implements PartitionSelectorStrategy {

    /**
     * <h1> 选择分区的策略</h1>
     * @param key
     * @param partitionCount
     * @return
     */
    @Override
    public int selectPartition(Object key, int partitionCount) {
        int partition = key.toString().hashCode() % partitionCount;
        log.info("SpringCloud Stream Qinyi Selector info: [{}], [{}], [{}]",
                key.toString(), partitionCount, partition);

        return partition;    }
}
