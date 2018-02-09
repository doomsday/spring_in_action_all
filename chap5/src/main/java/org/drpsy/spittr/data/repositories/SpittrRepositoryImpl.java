package org.drpsy.spittr.data.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.drpsy.spittr.web.SpittrStatuses;

/**
 * Created by drpsy on 10-Feb-18 (01:00).
 */
public class SpittrRepositoryImpl {

  @PersistenceContext
  private EntityManager em;

  public int eliteSwap() {
    return em.createQuery(
        "UPDATE Spittr s "
            + "SET s.status = '" + SpittrStatuses.ELITE + "' "
            + "WHERE s.status = '" + SpittrStatuses.NEWBIE + "' AND "
            + "s.id IN (SELECT s FROM Spittr s WHERE (SELECT COUNT(s) FROM Spittle s ) > 0)")
        .executeUpdate();
  }

}
