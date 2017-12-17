package org.drpsy.spittr.data.repositories;

import org.drpsy.spittr.Spittle;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 11-Nov-17 (13:55).
 */
@Repository
public interface SpittleRepository extends PagingAndSortingRepository<Spittle, Long> {

}
