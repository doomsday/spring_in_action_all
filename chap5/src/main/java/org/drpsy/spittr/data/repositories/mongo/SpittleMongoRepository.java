package org.drpsy.spittr.data.repositories.mongo;

import org.drpsy.spittr.data.mongo.documents.Spittle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 13-Jan-18 (23:12).
 */
@Repository
public interface SpittleMongoRepository extends PagingAndSortingRepository<Spittle, Long> {}
