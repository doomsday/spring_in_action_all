package org.drpsy.spittr.data.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.drpsy.spittr.data.entities.Spittle;
import org.drpsy.spittr.data.entities.Spittr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 16-Jan-18 (00:22).
 */
@Repository
@Transactional
public class JdbcSpittrRepository implements SpittrRepository {

  // Catches any SQLExceptions and then translates it into one of the more specific
  // data-access exceptions.
  @Autowired
  private JdbcOperations jdbcOperations;

  private static final String INSERT_SPITTR =
      "INSERT INTO `users` (`username`, `password`, `enabled`, `firstname`, `lastname`, `email`, `photo_uuid`) "
          + "VALUES (:username, :password, :enabled, :firstname, :lastname, :email, :photo_uuid)";

  private static final String FIND_BY_USERNAME =
      "SELECT * FROM `users` WHERE `username` = :username";

  @Override
  public Optional<Spittr> findByUsername(String username) {
    try {
      return Optional.ofNullable(jdbcOperations.queryForObject(FIND_BY_USERNAME, Spittr.class, username));
    } catch (EmptyResultDataAccessException ignored) {
      return Optional.empty();
    }
  }

  // When the update() method is called, JdbcTemplate gets a connection,
  // creates a statement, and executes the insert SQL.
  @Override
  public void save(Spittr spittr) {
    Map<String, Object> params = new HashMap<>();
    params.put("username", spittr.getUsername());
    params.put("password", spittr.getPassword());
    params.put("enabled", spittr.getEnabled());
    params.put("firstname", spittr.getFirstName());
    params.put("lastname", spittr.getLastName());
    params.put("email", spittr.getEmail());
    params.put("photo_uuid", spittr.getPhotoUUID());

    jdbcOperations.update(INSERT_SPITTR, params);
  }
}
