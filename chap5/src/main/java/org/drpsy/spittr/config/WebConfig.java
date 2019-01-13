package org.drpsy.spittr.config;

import java.net.MalformedURLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Created by drpsy on 10-Nov-17 (18:45).
 */
@Configuration
@EnableWebMvc
@EnableMBeanExport
@ComponentScan("org.drpsy.spittr.web")
public class WebConfig implements ApplicationContextAware, WebMvcConfigurer {

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  // Get a ContentNegotiationManager. ContentNegotiationConfigurer has several methods that mirror the setter methods
  // of ContentNegotiationManager and enable you to set whatever content-negotiation behavior you’d like on the
  // ContentNegotiationManager that will be created.
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.defaultContentType(MediaType.TEXT_HTML); // Default to HTML
  }

  @Bean
  public View spittles() {
    return new MappingJackson2JsonView(); // 'spittles' JSON view
  }

  @Bean
  public ViewResolver beanNameViewResolver() {
    return new BeanNameViewResolver();  // Look up views as beans
  }

  // Configure and get ContentNegotiatingViewResolver. ContentNegotiatingViewResolver doesn't resolve views on its own.
  // Instead, it delegates to other view resolvers, asking them to resolve the view.
  @Bean
  public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
    ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
    cnvr.setContentNegotiationManager(cnm);
    return cnvr;
  }

  // Resolves Thymeleaf template views from logical view names.
  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine());
    resolver.setCharacterEncoding("UTF-8");
    return resolver;
  }

  // Process the templates and render the results.
  @Bean
  public ISpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setEnableSpringELCompiler(true);
    engine.setTemplateResolver(templateResolver());
//    engine.addDialect(new SpringSecurityDialect());
    return engine;
  }

  // Loads Thymeleaf templates.
  private ITemplateResolver templateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("/WEB-INF/templates/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode(TemplateMode.HTML);
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

  // Loads messages from a properties file whose name is derived from a base name.
  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
  }

  // Enables Servlet 3.0 support for multipart requests.
  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  /*  JMX Remote */

  // Exposing remote MBeans

  // ConnectorServerFactoryBean creates and starts a JSR-160 JMXConnectorServer.
  @Bean
  @DependsOn("rmiRegistryFB")
  public ConnectorServerFactoryBean connectorServerFactoryBean() {
    // Default address: "service:jmx:jmxmp://localhost:9875".
    ConnectorServerFactoryBean csfb = new ConnectorServerFactoryBean();
    // Change default address.
    csfb.setServiceUrl(
        "service:jmx:rmi://localhost/jndi/rmi://localhost:1099/spitter");
    return csfb;
  }

  // You may have several remoting protocol options to choose from, including Remote Method Invocation (RMI),
  // SOAP, Hessian/Burlap, and even Internet InterORB Protocol (IIOP). In this example, we use RMI registry running and
  // listening at 1099.
  @Bean
  public RmiRegistryFactoryBean rmiRegistryFB() {
    RmiRegistryFactoryBean rmiRegistryFB = new RmiRegistryFactoryBean();
    rmiRegistryFB.setPort(1099);
    return rmiRegistryFB;
  }

  // Accessing remote MBeans

  // Bean than can be used to access the RMI-based remote server you created in the previous section.
  // It is a factory bean, that creates an MBeanServerConnection which acts as a local proxy to the remote MBean server.
  @Bean
  public MBeanServerConnectionFactoryBean connectionFactoryBean() throws MalformedURLException {
    MBeanServerConnectionFactoryBean mbscfb = new MBeanServerConnectionFactoryBean();
    mbscfb.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/spitter");
    return mbscfb;
  }

}
