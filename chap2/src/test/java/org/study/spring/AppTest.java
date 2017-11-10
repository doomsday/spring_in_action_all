package org.study.spring;

import com.springinaction.aspects.introducers.Encoreable;
import com.springinaction.concert.Performance;
import com.springinaction.configuration.AppConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppTest extends TestCase {

  @Autowired
  private Performance performance;

  @Test
  public void introductionShallIntroduce() {
    performance.perform();

    Encoreable encoreablePerformance = (Encoreable) performance;
    encoreablePerformance.performEncore();
  }
}
