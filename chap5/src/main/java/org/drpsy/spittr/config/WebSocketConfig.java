package org.drpsy.spittr.config;

import org.drpsy.spittr.web.socket.WebSocketChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by drpsy on 25-Sep-18 (01:28).
 * Configures Spring to dispatch WebSocket messages to it.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Bean
  public WebSocketChatHandler marcoHandler() {
    return new WebSocketChatHandler();
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    // Map WebSocketChatHandler to "/marco"
    webSocketHandlerRegistry.addHandler(marcoHandler(), "/marco");
  }

}