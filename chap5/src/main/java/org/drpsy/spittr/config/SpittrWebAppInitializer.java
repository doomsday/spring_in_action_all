package org.drpsy.spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.drpsy.spittr.utils.PropertiesConfigReader;
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

  /*
  The javadoc for Part.write() says it will write relative to the MultipartConfig.location. I bet your configuration has
  MultipartConfig.location set to java.io.tmpdir (the default value), hence why your transferTo() is prefixed with
  "/tmp" [https://dev.eclipse.org/mhonarc/lists/jetty-users/msg08332.html]
   */

  private final String TMP_LOCATION = new PropertiesConfigReader()
      .getPropValue("tmp.dir")
      .orElse(System.getProperty("java.io.tmpdir")); // Temporary location where files will be stored
  private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size. Beyond that size spring will throw exception.
  private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
  private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk

  // Map DispatcherServlet to. '/' indicating that it is the application's default servlet.
  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
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
        new MultipartConfigElement(TMP_LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
  }

}
