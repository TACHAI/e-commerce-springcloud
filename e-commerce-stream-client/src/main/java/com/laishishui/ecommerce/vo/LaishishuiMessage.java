package com.laishishui.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tachai on 2022/6/12 8:22 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaishishuiMessage {

    private Integer id;
    private String projectName;
    private String org;
    private String author;
    private String version;

    /**
     * <h2>返回一个默认的消息, 方便使用</h2>
     * */
    public static LaishishuiMessage defaultMessage() {

        return new LaishishuiMessage(
                1,
                "e-commerce-stream-client",
                "laishishui.com",
                "laishishui",
                "1.0"
        );
    }
}
