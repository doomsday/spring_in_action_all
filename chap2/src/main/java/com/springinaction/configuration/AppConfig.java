package com.springinaction.configuration;

import com.springinaction.aspects.introducers.Encoreable;
import com.springinaction.concert.App;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by drpsy on 23-Oct-17 (22:06).
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {App.class, Encoreable.class})
public class AppConfig {

}
