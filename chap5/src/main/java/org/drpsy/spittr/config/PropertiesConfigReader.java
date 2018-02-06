package org.drpsy.spittr.config;

import com.google.common.base.Strings;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 20-Dec-17 (01:03).
 */
@Component
public class PropertiesConfigReader {

  @Autowired
  private Logger log;

  private static final String CONFIG_NAME = "config.properties";

  public Optional<String> getPropValue(String propKey) {

    if (Strings.isNullOrEmpty(propKey)) {
      return Optional.empty();
    }

    InputStream is = this.getClass().getClassLoader().getResourceAsStream(CONFIG_NAME);

    Properties prop = new Properties();

    try {
      prop.load(is);
      Optional<String> value = Optional.ofNullable(prop.getProperty(propKey));
      if (!value.isPresent()) {
        log.warn("Property is not found in \'" + CONFIG_NAME + "\'");
      }
      return value;
    } catch (IOException ignored) {
      log.error("Error occurred while loading properties file: \'" + CONFIG_NAME + "\'");
      return Optional.empty();
    }

  }

}
