package org.drpsy.spittr.service;

import org.drpsy.spittr.data.neo4j.documents.Spittr;
import org.drpsy.spittr.data.repositories.neo4j.SpittrNeo4jRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 02-Jun-18 (21:54).
 */
@Component
public class SpittrServiceImpl implements SpittrService {

  @Autowired
  private SpittrNeo4jRepository repository;

  @Override
  public Spittr findByUsername(String username) {
    Spittr spittr = repository.findByUsername(username);
    if (spittr == null) {
      spittr = new Spittr();
      String NOT_FOUND = "NOT FOUND";
      spittr.setUsername(NOT_FOUND);
      spittr.setLastName(NOT_FOUND);
      spittr.setEmail(NOT_FOUND);
    }
    return spittr;
  }

}
