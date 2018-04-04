package org.drpsy.spittr.data.repositories.neo4j;

import org.drpsy.spittr.data.neo4j.documents.Spittr;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 14-Jan-18 (13:04).
 */
@Repository
public interface UserNeo4jRepository extends Neo4jRepository<Spittr, Long> {

  Spittr findByUsername(String username);

}
