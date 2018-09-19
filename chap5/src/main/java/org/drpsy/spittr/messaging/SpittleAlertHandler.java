package org.drpsy.spittr.messaging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drpsy.spittr.data.mongo.documents.Spittle;
import org.springframework.stereotype.Component;


/**
 * Created by drpsy on 25-Aug-18 (00:06).
 */

@Component
public class SpittleAlertHandler{
  private static final Logger LOGGER = LogManager.getLogger(SpittleAlertHandler.class);

  public void handleSpittleAlert(Spittle spittle) {
    LOGGER.info("Spittle message is: \'" + spittle.getMessage() + "\'");
  }

}
