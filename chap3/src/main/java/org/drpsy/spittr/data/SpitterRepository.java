package org.drpsy.spittr.data;

import org.drpsy.spittr.Spitter;

/**
 * Created by drpsy on 16-Nov-17 (23:21).
 */
public interface SpitterRepository {

  Spitter save(Spitter spitter);

  Spitter findByUserName(String userName);
}
