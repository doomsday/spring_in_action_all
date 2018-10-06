package org.drpsy.spittr.web.controllers;

import java.security.Principal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drpsy.spittr.data.Shout;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by drpsy on 05-Oct-18 (01:30).
 */
@Controller
public class StompController {

  private static final Logger LOGGER = LogManager.getLogger(StompController.class);

  /**
   * Handle messages to /app/stomp_shout. When an @MessageMapping-annotated method has a return value,
   * the returned object will be converted (via a message converter) and placed into the payload of
   * a STOMP frame and published to the broker.
   * @param incoming Incoming message from message destination.
   */
  @MessageMapping("/stomp_shout")
  @SendTo("/topic/stomp_echo")  // Overriding default value: "/topic/stomp_shout"
  public Shout handleShout(Shout incoming) {
    String incomingMessage = incoming.getMessage();
    LOGGER.info("Received message: " + incomingMessage);

    return new Shout("Received [/app/stomp_shout]: " + incomingMessage);
  }

  /**
   * Handle messages to /app/stomp_pers_shout. Sends messages to /user/topic/stomp_pers_echo. /user messages
   * flow through UserDestinationMessageHandler. Its primary job is to reroute user messages to a destination
   * that's unique to the user. In the case of a subscription, it derives the target destination by removing
   * the /user prefix and adding a suffix that's based on the user's session. For instance, a subscription
   * to /user/queue/notifications may end up being rerouted to a destination named /queue/notifications-user6hr83v6t
   * @param principal
   * @param incoming
   * @return
   */
  @MessageMapping("/stomp_pers_shout")
  @SendToUser("/topic/stomp_pers_echo")
  public Shout handlePersMessage(Principal principal, Shout incoming) {
    String incomingMessage = incoming.getMessage();
    LOGGER.info("Received message: " + incomingMessage);

    return new Shout(
        String.format("Received [/app/stomp_shout] for user %s: %s",
        principal.getName(), incomingMessage)
    );
  }

  /**
   * @SubscribeMapping is to implement a request-reply pattern. In the request-reply pattern, the
   * client subscribes to a destination expecting a one-time response at that destination.
   * @return One-time respons of Shout type.
   */
  @SubscribeMapping("/stomp_shout") // /app/stomp_shout
  public Shout handleSubscription() {
    return new Shout("Subscribed to: /app/stomp_shout");
  }

  @SubscribeMapping("/stomp_pers_shout") // /app/stomp_pers_shout
  public Shout handlePersSubscription() {
    return new Shout("Subscribed to: /app/stomp_pers_shout");
  }

}
