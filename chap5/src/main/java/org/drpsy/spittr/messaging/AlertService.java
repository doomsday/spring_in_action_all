package org.drpsy.spittr.messaging;

import org.drpsy.spittr.data.mongo.documents.Spittle;

/**
 * Created by drpsy on 24-Aug-18 (00:05).
 */
public interface AlertService {

  /**
   * Sending spittle alerts asynchronously.
   * @param spittle
   */
  void sendSpittleAlert(Spittle spittle);

}
