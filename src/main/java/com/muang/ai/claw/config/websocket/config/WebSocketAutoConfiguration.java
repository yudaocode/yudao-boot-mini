package com.muang.ai.claw.config.websocket.config;

import com.muang.ai.claw.config.websocket.core.handler.JsonWebSocketMessageHandler;
import com.muang.ai.claw.config.websocket.core.listener.WebSocketMessageListener;
import com.muang.ai.claw.config.websocket.core.security.LoginUserHandshakeInterceptor;
import com.muang.ai.claw.config.websocket.core.security.WebSocketAuthorizeRequestsCustomizer;
import com.muang.ai.claw.config.websocket.core.sender.local.LocalWebSocketMessageSender;
import com.muang.ai.claw.config.websocket.core.session.WebSocketSessionHandlerDecorator;
import com.muang.ai.claw.config.websocket.core.session.WebSocketSessionManager;
import com.muang.ai.claw.config.websocket.core.session.WebSocketSessionManagerImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;

@AutoConfiguration
@EnableWebSocket
@ConditionalOnProperty(prefix = "yudao.websocket", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(WebSocketProperties.class)
public class WebSocketAutoConfiguration {

    @Bean
    public WebSocketConfigurer webSocketConfigurer(HandshakeInterceptor[] handshakeInterceptors,
                                                   WebSocketHandler webSocketHandler,
                                                   WebSocketProperties webSocketProperties) {
        return registry -> registry
                .addHandler(webSocketHandler, webSocketProperties.getPath())
                .addInterceptors(handshakeInterceptors)
                .setAllowedOriginPatterns("*");
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new LoginUserHandshakeInterceptor();
    }

    @Bean
    public WebSocketHandler webSocketHandler(WebSocketSessionManager sessionManager,
                                             List<? extends WebSocketMessageListener<?>> messageListeners) {
        JsonWebSocketMessageHandler messageHandler = new JsonWebSocketMessageHandler(messageListeners);
        return new WebSocketSessionHandlerDecorator(messageHandler, sessionManager);
    }

    @Bean
    public WebSocketSessionManager webSocketSessionManager() {
        return new WebSocketSessionManagerImpl();
    }

    @Bean
    public WebSocketAuthorizeRequestsCustomizer webSocketAuthorizeRequestsCustomizer(WebSocketProperties webSocketProperties) {
        return new WebSocketAuthorizeRequestsCustomizer(webSocketProperties);
    }

    @Bean
    public LocalWebSocketMessageSender localWebSocketMessageSender(WebSocketSessionManager sessionManager) {
        return new LocalWebSocketMessageSender(sessionManager);
    }

}
