package org.drpsy.spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Any class that extends AbstractAnnotationConfigDispatcherServletInitializer will automatically be used to configure
 * DispatcherServlet and the Spring application context in the application's servlet context.
 * It will be automatically discovered when deployed in a Servlet 3.0 container and be used to configure the servlet
 * context.
 *
 * Under the covers, DispatcherServlet and ContextLoadListener will be created.
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  // Map DispatcherServlet to. '/' indicating that it is the application's default servlet.
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }

  // Load beans containing web components: controllers, view resolvers and handler mappings.
  // Returns @Configuration classes that will define beans for DispatcherServlet.
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{WebConfig.class};
  }

  // ContextLoadListener: Load other beans in the application: middle-tier, data-tier that drive back end.
  // Returns @Configuration classes that will be used to configure the app. context created by ContextLoadListener.
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{RootConfig.class};
  }

  // Enables support for multipart requests.
  @Override
  protected void customizeRegistration(Dynamic registration) {
    registration.setMultipartConfig(
        new MultipartConfigElement(
            // {tmpdir}/spittr/uploads
            System.getProperty("java.io.tmpdir") + "spittr/uploads",
            2097152,
            4194304,
            0
        ));
  }

}
