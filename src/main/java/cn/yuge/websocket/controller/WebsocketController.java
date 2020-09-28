package cn.yuge.websocket.controller;

import cn.yuge.common.result.ResultTemplate;
import cn.yuge.websocket.service.MyWebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luojiayu
 * @date 2020/9/28
 */
@RestController
@RequestMapping("/websocket")
public class WebsocketController {

    @Resource
    private MyWebSocket myWebSocket;

    @GetMapping("/send")
    public ResultTemplate send() {
        for (int i = 0; i < 1000; i++) {
            myWebSocket.onMessage("这是消息:" + i);
        }
        return ResultTemplate.ok();
    }
}
