package org.drpsy.spittr.web.services;

import org.drpsy.spittr.data.neo4j.documents.Spittle;

/**
 * Created by drpsy on 07-Oct-18 (11:48).
 */
public interface SpittleFeedService {

  void broadcastSpittle(Spittle spittle);

  void broadcastPersSpittle(Spittle spittle);

}
