package com.laishishui.ecommerce.controller;

import com.laishishui.ecommerce.goods.GoodsInfo;
import com.laishishui.ecommerce.service.async.AsyncTaskManager;
import com.laishishui.ecommerce.vo.AsyncTaskInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tachai on 2022/1/19 7:58 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Api(tags="商品异步入库服务")
@Slf4j
@RestController
@RequestMapping("/async-goods")
public class AsyncGoodsGontroller {
    private final AsyncTaskManager asyncTaskManager;

    public AsyncGoodsGontroller(AsyncTaskManager asyncTaskManager){
        this.asyncTaskManager=asyncTaskManager;
    }
    @ApiOperation(value = "导入商品", notes="导入商品进入到商品表",httpMethod = "POST")
    @PostMapping("/import-goods")
    public AsyncTaskInfo importGoods(@RequestBody List<GoodsInfo>goodsInfos){
        return asyncTaskManager.submit(goodsInfos);
    }
    @ApiOperation(value = "查询状态",notes = "查询异步任务的执行状态",httpMethod = "GET")
    @GetMapping("/task-info")
    public AsyncTaskInfo getTaskInfo(@RequestParam String taskId){
        return asyncTaskManager.getTaskInfo(taskId);
    }
}
