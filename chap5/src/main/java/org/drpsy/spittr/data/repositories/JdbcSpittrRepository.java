package org.drpsy.spittr.data.repositories;

import java.util.Optional;
import javax.transaction.Transactional;
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

  @Autowired
  private JdbcOperations jdbcOperations;

  private static final String INSERT_SPITTR =
      "INSERT INTO users (username, password, enabled, firstname, lastname, email, photo_uuid) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?)";

  private static final String FIND_BY_USERNAME =
      "SELECT * FROM users WHERE username = ?";

  @Override
  public Optional<Spittr> findByUserName(String userName) {
    try {
      return Optional.ofNullable(jdbcOperations.queryForObject(FIND_BY_USERNAME, Spittr.class, userName));
    } catch (EmptyResultDataAccessException ignored) {
      return Optional.empty();
    }
  }

  @Override
  public void save(Spittr spittr) {
    jdbcOperations.update(INSERT_SPITTR,
        spittr.getUserName(),
        spittr.getPassword(),
        spittr.getEnabled(),
        spittr.getFirstName(),
        spittr.getLastName(),
        spittr.getEmail(),
        spittr.getPhotoUUID()
    );
  }
}
