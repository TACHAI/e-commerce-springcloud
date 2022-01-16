package com.laishishui.ecommerce.service.async;

import com.laishishui.ecommerce.goods.GoodsInfo;

import java.util.List;

/**
 * <h1>异步服务接口定义</h1>
 * @author tachai on 2022/1/16 7:03 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface IAsyncService {

    /**
     * <h2>异步将商品信息保存下来</h2>
     * @param goodsInfos
     * @param taskId
     */
    void asyncImportGoods(List<GoodsInfo> goodsInfos, String taskId);

}
