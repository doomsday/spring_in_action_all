package org.drpsy.spittr.data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

      spittleList.add(1, new Spittle("Spittles go fourth!",
          DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0));
      spittleList.add(2, new Spittle("Spittle spittle spittle",
          DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0));
      spittleList.add(3, new Spittle("Here's another spittle",
          DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0));
      spittleList.add(4,new Spittle("Hello world! The first ever spittle!",
          DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0));

    } catch (ParseException ignored) {}

    return spittleList;
  }

  @Override
  public Spittle findOne(long id) throws ParseException {
    String datePattern = "yyyy-MM-dd";
    return new Spittle("Spittles go fourth!",
        DateUtils.parseDate("2013-09-01", datePattern), 0.0, 0.0);
  }
}
