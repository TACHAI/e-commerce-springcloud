package com.laishishui.ecommerce.vo;

import com.laishishui.ecommerce.constant.AsyncTaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1>异步任务执行信息</h1>
 * @author tachai on 2022/1/16 7:06 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsyncTaskInfo {
    /**异步任务 id*/
    private String taskId;

    private AsyncTaskStatusEnum status;

    /**异步任务开始时间 */
    private Date startTime;
    /**异步任务结束时间*/
    private Date endTime;
    /**异步任务总耗时*/
    private String totalTime;
}
