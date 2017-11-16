package org.drpsy.spittr.data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.drpsy.spittr.Spittle;
import org.springframework.stereotype.Repository;

/**
 * Created by drpsy on 11-Nov-17 (19:52).
 */
@Repository
public class SpittleRepositoryStubImpl implements SpittleRepository {

  @Override
  public List<Spittle> findSpittles(long max, int count) {
    String datePattern = "yyyy-MM-dd";
    List<Spittle> spittleList = new ArrayList<>();

    try {

      spittleList.add(new Spittle(1L, "Spittles go fourth!",
          DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0));
      spittleList.add(new Spittle(2L, "Spittle spittle spittle",
          DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0));
      spittleList.add(new Spittle(3L, "Here's another spittle",
          DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0));
      spittleList.add(new Spittle(4L, "Hello world! The first ever spittle!",
          DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0));

    } catch (ParseException ignored) {}

    return spittleList;
  }

  @Override
  public Spittle findOne(long id) {
    String datePattern = "yyyy-MM-dd";
    Spittle spittle = null;

    try {
    spittle = new Spittle("Spittles go fourth!",
        DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0);
    } catch (ParseException ignored) {}

    return spittle;
  }
}
