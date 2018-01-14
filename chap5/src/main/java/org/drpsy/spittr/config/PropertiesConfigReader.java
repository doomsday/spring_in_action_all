package org.drpsy.spittr.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 20-Dec-17 (01:03).
 */
@Component
public class PropertiesConfigReader {

  public Optional<String> getPropValue(String propKey) {

    if (propKey == null || propKey.trim().isEmpty()) {
      return Optional.empty();
    }

    InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties");

    Properties prop = new Properties();

    try {
      prop.load(is);
      return Optional.of(prop.getProperty(propKey));
    } catch (IOException ignored) {
      return Optional.empty();
    }

  }

}
