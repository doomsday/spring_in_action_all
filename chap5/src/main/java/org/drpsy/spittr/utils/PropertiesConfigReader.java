package org.drpsy.spittr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by drpsy on 19-Sep-18 (13:32).
 */
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
