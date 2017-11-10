package com.springinaction.aspects.introducers;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 10-Nov-17 (14:37).
 */
@Aspect
@Component
public class EncoreableIntroducer {

  @DeclareParents(
      value = "com.springinaction.concert.Performance+",
      defaultImpl = EncoreableDelegate.class
  )
  public static Encoreable encoreable;

}
