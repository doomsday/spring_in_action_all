package org.drpsy.spittr.producers;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.drpsy.spittr.annotations.Spittr;

/**
 * Created by drpsy on 20-Dec-17 (00:41).
 */
public class DatabaseProducer {

  @Produces
  @Spittr
  @PersistenceContext(unitName = "spittr")
  private EntityManager em;

}
