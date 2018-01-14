package org.drpsy.spittr.data.repositories.mongo;

import org.drpsy.spittr.data.mongo.documents.Spittr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 14-Jan-18 (15:50).
 */
@Repository
public interface SpittrMongoRepository extends CrudRepository<Spittr, Long> {

  Spittr findByUsername(String username);

}
