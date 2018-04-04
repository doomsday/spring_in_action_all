package org.drpsy.spittr.config;

import org.drpsy.spittr.data.neo4j.documents.Spittle;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by drpsy on 21-Dec-17 (01:26).
 */
@Configuration
public class RESTConfig extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config
        .setPageParamName("page")
        .setLimitParamName("count")
        .exposeIdsFor(Spittle.class);
  }

}
