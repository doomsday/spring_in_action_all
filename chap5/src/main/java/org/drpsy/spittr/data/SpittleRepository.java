package org.drpsy.spittr.data;

import java.util.Date;
import java.util.List;
import org.drpsy.spittr.Spittle;

/**
 * Created by drpsy on 11-Nov-17 (13:55).
 */
public interface SpittleRepository {
  List<Spittle> findSpittles(long max, int count);

  Spittle findOne(long id);

  void save(Long id, String message, Date date, Double longitude, Double latitude);
}
