package org.drpsy.spittr.data.repositories;

import java.util.Optional;
import javax.transaction.Transactional;
import org.drpsy.spittr.data.entities.Spittle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 11-Nov-17 (13:55).
 */

public interface SpittleRepository {

  Page<Spittle> findAll(PageRequest of);

  Optional<Spittle> findById(long spittleId);

  void save(Spittle spittle);
}
