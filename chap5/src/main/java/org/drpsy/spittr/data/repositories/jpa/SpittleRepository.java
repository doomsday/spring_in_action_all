package org.drpsy.spittr.data.repositories.jpa;

import javax.transaction.Transactional;
import org.drpsy.spittr.data.entities.Spittle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 11-Nov-17 (13:55).
 */
@Repository
@Transactional
public interface SpittleRepository extends PagingAndSortingRepository<Spittle, Long> {}
