package org.drpsy.spittr.data;

import org.drpsy.spittr.Spitter;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 16-Nov-17 (23:35).
 */
@Repository
public class SpitterRepositoryStubImpl implements SpitterRepository {

  @Override
  public Spitter save(Spitter spitter) {
    return null;
  }

  @Override
  public Spitter findByUserName(String userName) {
    return new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer");
  }
}
