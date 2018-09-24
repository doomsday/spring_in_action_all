package org.drpsy.spittr.web.socket;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by drpsy on 25-Sep-18 (00:41).
 */
public class WebSocketChatHandler extends TextWebSocketHandler {

  private static final Logger LOGGER = LogManager.getLogger(TextWebSocketHandler.class);

  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException{
    String reply = "Received message: " + message.getPayload();
    LOGGER.info(reply);
    session.sendMessage(new TextMessage(reply));
  }

}
