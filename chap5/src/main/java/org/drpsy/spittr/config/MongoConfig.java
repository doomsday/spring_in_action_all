package org.drpsy.spittr.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by drpsy on 13-Jan-18 (18:05).
 */
@Configuration
@EnableMongoRepositories(basePackages = "org.drpsy.spittr.data.repositories.mongo")
public class MongoConfig {

  @Bean
  public MongoClientFactoryBean mongo() {
    MongoClientFactoryBean mongo = new MongoClientFactoryBean();
    mongo.setHost("localhost");
    return mongo;
  }

  @Bean
  public MongoOperations mongoTemplate(MongoClient mongo) {
    return new MongoTemplate(mongo, "SpittlesDB");
  }

}
