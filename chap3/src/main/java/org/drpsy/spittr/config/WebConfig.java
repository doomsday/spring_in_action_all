package org.drpsy.spittr.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by drpsy on 10-Nov-17 (18:45).
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.drpsy.spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {

  @Bean
  public ViewResolver viewResolver() {
    return new TilesViewResolver();
  }

  // Configure static content handler: forward requests for static resources to
  // the container's default servlet and do not try to handle them itself.
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  // Serve static resources.
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("resources/**") // External facing URI path...
        .addResourceLocations("/resources/"); // map it to the physical path where the resources are actually located.
  }

  // Loads messages from a properties file whose name is derived from a base name.
  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
  }

  // Locates and loads tile definitions and generally coordinate Tiles.
  @Bean
  public TilesConfigurer tilesConfigurer() {
    TilesConfigurer tiles = new TilesConfigurer();
    tiles.setDefinitions("WEB-INF/layout/tiles.xml"); // Specify tile definition locations.
    tiles.setCheckRefresh(true);  // Enable refresh.
    return tiles;
  }



}
