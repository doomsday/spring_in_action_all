package org.drpsy.spittr.data.repositories;

import java.util.Optional;
import javax.transaction.Transactional;
import org.drpsy.spittr.data.entities.Spittr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 16-Nov-17 (23:21).
 */
public interface SpittrRepository {

  Optional<Spittr> findByUsername(String username);

  void save(Spittr spittr);
}
