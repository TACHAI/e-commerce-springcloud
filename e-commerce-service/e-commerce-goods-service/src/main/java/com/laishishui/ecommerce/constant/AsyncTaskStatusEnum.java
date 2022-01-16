package com.laishishui.ecommerce.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>异步任务状态枚举</h1>
 * @author tachai on 2022/1/16 8:34 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Getter
@AllArgsConstructor
public enum  AsyncTaskStatusEnum {

    STARTED(0,"已经启动"),
    RUNNING(1,"正在运行"),
    SUCCESS(2,"执行成功"),
    FAILED(3,"执行失败"),
    ;

    /**执行状态编码*/
    private final int state;
    /**执行状态描述*/
    private final String stateInfo;
}
