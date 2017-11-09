package com.springinaction.configuration;

import com.springinaction.spelsystem.App;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by drpsy on 23-Oct-17 (22:06).
 */
@Configuration
@Profile("dev")
@ComponentScan(basePackageClasses = {App.class})
@PropertySource("classpath:app.properties")
public class AppConfig {

}

