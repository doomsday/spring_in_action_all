package org.drpsy.spittr.data.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.drpsy.spittr.data.entities.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 16-Jan-18 (01:15).
 */
@Repository
@Transactional
public class JdbcSpittleRepository implements SpittleRepository {

  @Autowired
  private JdbcOperations jdbcOperations;

  private static final String FIND_BY_ID = "SELECT * FROM `spittle` WHERE `id` = :spittleId";

  private static final String INSERT_SPITTLE = "INSERT INTO `spittle` (`message`, `time`, `latitude`, `longitude`) "
      + "VALUES (:message, :time, :latitude, :longitude)";

  @Override
  public Page<Spittle> findAll(PageRequest of) {
    return null;
  }

  @Override
  public Optional<Spittle> findById(long spittleId) {
    try {
      return Optional.ofNullable(jdbcOperations.queryForObject(FIND_BY_ID, Spittle.class, spittleId));
    } catch (EmptyResultDataAccessException ignored) {
      return Optional.empty();
    }
  }

  @Override
  public void save(Spittle spittle) {
    Map<String, Object> params = new HashMap<>();
    params.put("message", spittle.getMessage());
    params.put("time", spittle.getTime());
    params.put("latitude", spittle.getLatitude());
    params.put("longitude", spittle.getLongitude());

    jdbcOperations.update(INSERT_SPITTLE, params);
  }
}
