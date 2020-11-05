package cn.yuge.websocket.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 基于STOMP的WebSocket配置类
 * 1.@EnableWebSocketMessageBroker注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。
 * 2.registerStompEndpoints方法表示注册STOMP协议的节点，并指定映射的URL
 * 3.addEndpoint(“/simple”).withSockJS(); 用来注册STOMP协议节点，同时指定使用SockJS
 * 4.configureMessageBroker方法用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
 *
 * @author luojiayu
 * @date 2020/9/29
 */
@Configuration
@EnableWebSocketMessageBroker
public class STOMPWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {


    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/simple")
            .setAllowedOrigins("*") //解决跨域问题
            .withSockJS();
    }


    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
    }
}
