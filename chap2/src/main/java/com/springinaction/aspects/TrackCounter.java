package com.springinaction.aspects;

import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 10-Nov-17 (01:26).
 */
@Aspect
@Component
public class TrackCounter {
  private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();

  @Pointcut("execution(* com.springinaction.soundsystem.CompactDisc.playTrack(int)) && args(trackNumber)")
  public void trackPlayed(int trackNumber) {}

  @Before(value = "trackPlayed(trackNumber)", argNames = "trackNumber")
  public void countTrack(int trackNumber) {
    int currentCount = getPlayCount(trackNumber);
    trackCounts.put(trackNumber, currentCount + 1);
  }

  public int getPlayCount(int trackNumber) {
    return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber) : 0;
  }
}
