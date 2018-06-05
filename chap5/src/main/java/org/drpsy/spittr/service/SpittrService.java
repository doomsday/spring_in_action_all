package org.drpsy.spittr.service;

import org.drpsy.spittr.data.neo4j.documents.Spittr;

/**
 * Created by drpsy on 02-Jun-18 (21:53).
 */
public interface SpittrService {

  Spittr findByUsername(String username);

}
