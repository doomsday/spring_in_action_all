package org.drpsy.spittr.data.repositories.mongo;

import org.drpsy.spittr.data.mongo.documents.Spittr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 14-Jan-18 (13:04).
 */
@Repository
public interface UserMongoRepository extends CrudRepository<Spittr, Long> {

  Spittr findByUsername(String username);

}
