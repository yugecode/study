package cn.yuge.async.controller;

import cn.yuge.async.task.Task;
import cn.yuge.common.result.ResultTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 异步线程
 *
 * @author luojiayu
 * @date 2020/9/28
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Resource
    private Task task;

    /**
     * 用于线程之间的同步,获取线程的信息
     *
     * @return
     */
    @GetMapping("/synchronization")
    public ResultTemplate asyncSynchronization() {
        //用Future获取异步线程执行的结果
        Future<String> run = task.run();
        String result = null;
        try {
            result = run.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(result)) {
            return ResultTemplate.builder()
                .code("200")
                .msg("success")
                .success(true)
                .data(result)
                .build();
        }
        //这是一个异步线程
        return ResultTemplate.builder()
            .code("200")
            .msg("success")
            .success(true)
            .data("result")
            .build();
    }

}
