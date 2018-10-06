package org.drpsy.spittr.config;

import org.drpsy.spittr.web.socket.WebSocketChatHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by drpsy on 25-Sep-18 (01:28).
 * Configures Spring to dispatch WebSocket messages to it.
 */
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker // Enable STOMP messaging
public class WebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

  /**
   * Registers message handlers.
   * @param webSocketHandlerRegistry
   */
  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    // Map WebSocketChatHandler to "/marco"
    webSocketHandlerRegistry.addHandler(new WebSocketChatHandler(), "/marco");
  }

  /**
   * Endpoint that a client would connect to before subscribing to or publishing to a destination path
   * @param registry
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/stomp_chat", "/stomp_spittr", "/stomp_person_message", "/stomp_person_spittr").withSockJS();
  }

  /**
   * This method is optional. If you don't override it, you'll get a simple in-memory message broker
   * configured to handle messages prefixed with /topic. But in this example, you override it so that
   * the message broker is responsible for messages prefixed with /topic and /queue.
   * @param registry
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // SimpleBrokerMessageHandler: application destinations are prefixed with /app (@MessageMapping-annotated
    // controller method).
    //registry.enableSimpleBroker("/queue", "/topic");

    // Back WebSocket messaging with a real STOMP-enabled broker, such as RabbitMQ or ActiveMQ.
    registry.enableStompBrokerRelay("/queue", "/topic");
    
    // AnnotatedMethodMessageHandler: the broker destinations are prefixed with either /topic or /queue
    registry.setApplicationDestinationPrefixes("/app");
  }

}