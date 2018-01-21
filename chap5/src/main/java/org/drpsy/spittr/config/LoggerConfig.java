package org.drpsy.spittr.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by drpsy on 21-Jan-18 (18:10).
 */
@Configuration
public class LoggerConfig {

  @Bean
  public Logger createLogger(InjectionPoint ip) {
    return LoggerFactory.getLogger(ip.getClass().getCanonicalName());
  }

}
