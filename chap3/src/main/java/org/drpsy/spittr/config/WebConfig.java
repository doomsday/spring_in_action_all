package org.drpsy.spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by drpsy on 10-Nov-17 (18:45).
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.drpsy.spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {

  // DispatcherServlet consults a view resolver to map the logical view name
  // received from controller to a specific view implementation.
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    resolver.setExposeContextBeansAsAttributes(true); // Make all such beans accessible in plain ${...}
    return resolver;
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

}
