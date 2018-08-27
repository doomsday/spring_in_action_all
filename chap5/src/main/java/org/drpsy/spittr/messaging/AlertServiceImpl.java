package org.drpsy.spittr.messaging;

import org.drpsy.spittr.data.mongo.documents.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 24-Aug-18 (00:07).
 */
@Component
public class AlertServiceImpl implements AlertService {

  private JmsOperations jmsOperations;

  @Autowired
  public AlertServiceImpl(JmsTemplate jmsTemplate) {
    this.jmsOperations = jmsTemplate;
  }

  @Override
  public void sendSpittleAlert(final Spittle spittle) {
    // Uses default destination "spittle.alert.queue"
    jmsOperations.convertAndSend(spittle);
  }

}
