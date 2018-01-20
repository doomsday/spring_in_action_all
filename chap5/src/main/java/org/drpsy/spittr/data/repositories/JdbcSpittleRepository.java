package org.drpsy.spittr.data.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.drpsy.spittr.data.entities.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 16-Jan-18 (01:15).
 */
@Repository
@Transactional
public class JdbcSpittleRepository implements SpittleRepository {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  private static final String FIND_BY_ID = "SELECT * FROM `spittle` WHERE `id` = :id";

  private static final String INSERT_SPITTLE = "INSERT INTO `spittle` (`message`, `time`, `latitude`, `longitude`) "
      + "VALUES (:message, :time, :latitude, :longitude)";

  private static final String FIND_ALL = "SELECT * FROM `spittle` ORDER BY `id` LIMIT :limit OFFSET :offset";

  @Override
  public Optional<Spittle> findById(long spittleId) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", spittleId);
      return Optional.ofNullable(
          jdbcTemplate.queryForObject(FIND_BY_ID, params, BeanPropertyRowMapper.newInstance(Spittle.class)));
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

    jdbcTemplate.update(INSERT_SPITTLE, params);
  }

  @Override
  public Page<Spittle> findAll(PageRequest of) {
    Map<String, Object> params = new HashMap<>();
    params.put("limit", of.getPageSize());
    params.put("offset", of.getOffset() * of.getPageSize());

    return new PageImpl<>(jdbcTemplate.query(FIND_ALL, params, BeanPropertyRowMapper.newInstance(Spittle.class)));
  }

}
