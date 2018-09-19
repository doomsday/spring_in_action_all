package org.drpsy.spittr.messaging;

import org.drpsy.spittr.data.mongo.documents.Spittle;
import org.drpsy.spittr.data.mongo.documents.Spittr;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 24-Aug-18 (00:07).
 */
@Component
public class AlertServiceImpl implements AlertService {

  @Autowired
  private RabbitTemplate rabbit;

  @Value("${spittle.routing.key}")
  private String spittleRoutingKey;

  @Value("${spittr.routing.key}")
  private String spittrRoutingKey;

  @Override
  public void sendSpittleAlert(final Spittle spittle) {
    rabbit.convertAndSend(spittleRoutingKey, spittle);
  }

  @Override
  public void sendSpittrAlert(Spittr spittr) {
    rabbit.convertAndSend(spittrRoutingKey, spittr);
  }

}
