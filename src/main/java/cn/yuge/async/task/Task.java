package cn.yuge.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 异步任务
 *
 * @author luojiayu
 * @date 2020/9/28
 */
@Slf4j
@Component
public class Task {

    public static Random random = new Random();


    @Async
    public Future<String> run() {

        long sleep = random.nextInt(10000);

        log.info("开始任务，需耗时：" + sleep + "毫秒");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("完成任务");

        return new AsyncResult<>("test");

    }
}
