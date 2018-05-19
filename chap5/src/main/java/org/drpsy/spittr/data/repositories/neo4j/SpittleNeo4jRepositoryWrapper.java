package org.drpsy.spittr.data.repositories.neo4j;

import java.util.Optional;
import org.drpsy.spittr.data.neo4j.documents.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 20-May-18 (01:52).
 */
@Component
public class SpittleNeo4jRepositoryWrapper {

  private final SpittleNeo4jRepository repository;

  @Autowired
  public SpittleNeo4jRepositoryWrapper(SpittleNeo4jRepository repository) {
    this.repository = repository;
  }

  public Spittle save(Spittle entity) {
    return repository.save(entity);
  }

  public Page<Spittle> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Cacheable("spittlesSearch")
  public Optional<Spittle> findById(Long spittleId) {
    return repository.findById(spittleId);
  }

  @PreAuthorize("hasPermission(#spittle, 'delete')")
  public void delete(Spittle spittle) {
    repository.delete(spittle);
  }

}
