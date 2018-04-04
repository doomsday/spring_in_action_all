package org.drpsy.spittr.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by drpsy on 13-Jan-18 (18:05).
 */
@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "org.drpsy.spittr.data.repositories.neo4j")
public class Neo4jConfig {

  @Autowired
  private Environment env;

  @Bean
  public SessionFactory sessionFactory() {
    // with domain entity base package(s)
    return new SessionFactory(configuration(),"org.drpsy.spittr.data.neo4j.documents");
  }

  @Bean
  public Neo4jTransactionManager transactionManager() {
    return new Neo4jTransactionManager(sessionFactory());
  }

  @Bean
  public org.neo4j.ogm.config.Configuration configuration() {
    return new org.neo4j.ogm.config.Configuration.Builder()
        .uri("bolt://localhost")
        .credentials(env.getProperty("db.neo4j.username"), env.getProperty("db.neo4j.password"))
        .build();
  }

}
