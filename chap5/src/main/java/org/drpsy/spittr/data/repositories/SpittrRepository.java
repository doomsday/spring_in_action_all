package org.drpsy.spittr.data.repositories;

import org.drpsy.spittr.Spittr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 16-Nov-17 (23:21).
 */
@Repository
public interface SpittrRepository extends CrudRepository<Spittr, Long> {

  Spittr findByUserName(String userName);

}
