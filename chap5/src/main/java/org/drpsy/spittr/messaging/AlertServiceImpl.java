package org.drpsy.spittr.messaging;

import org.drpsy.spittr.data.mongo.documents.Spittle;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 24-Aug-18 (00:07).
 */
@Component
public class AlertServiceImpl implements AlertService {

  @Autowired
  private RabbitTemplate rabbit;

  @Override
  public void sendSpittleAlert(final Spittle spittle) {
    rabbit.convertAndSend(spittle);
  }

}
