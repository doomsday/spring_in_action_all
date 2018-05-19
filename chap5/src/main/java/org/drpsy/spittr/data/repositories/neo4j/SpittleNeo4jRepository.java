package org.drpsy.spittr.data.repositories.neo4j;

import java.util.Optional;
import org.drpsy.spittr.data.neo4j.documents.Spittle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 13-Jan-18 (23:12).
 */
@Repository
public interface SpittleNeo4jRepository extends PagingAndSortingRepository<Spittle, Long> {

  @Override
  Optional<Spittle> findById(Long spittleId);

  @Override
  void delete(Spittle spittle);

}
