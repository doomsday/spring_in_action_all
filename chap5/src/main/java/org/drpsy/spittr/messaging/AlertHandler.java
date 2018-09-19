package org.drpsy.spittr.messaging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drpsy.spittr.data.mongo.documents.Spittle;
import org.drpsy.spittr.data.mongo.documents.Spittr;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * Created by drpsy on 25-Aug-18 (00:06).
 */

@Component
public class AlertHandler {
  private static final Logger LOGGER = LogManager.getLogger(AlertHandler.class);

  @RabbitListener(queues = "spittle.queue")
  public void handleSpittleAlert(Spittle spittle) {
    LOGGER.info("Spittle message is: \'" + spittle.getMessage() + "\'");
  }

  @RabbitListener(queues = "spittr.queue")
  public void handleSpittrAlert(Spittr spittr) {
    LOGGER.info("Spittr email registered is: " + spittr.getEmail() + "\'");
  }

}
