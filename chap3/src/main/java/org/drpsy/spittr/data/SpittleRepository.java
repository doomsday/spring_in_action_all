package org.drpsy.spittr.data;

import java.text.ParseException;
import java.util.List;
import org.drpsy.spittr.Spittle;

/**
 * Created by drpsy on 11-Nov-17 (13:55).
 */
public interface SpittleRepository {
  List<Spittle> findSpittles(long max, int count);

  Spittle findOne(long id) throws ParseException;
}
