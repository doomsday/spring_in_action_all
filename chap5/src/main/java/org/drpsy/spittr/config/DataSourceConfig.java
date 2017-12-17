package org.drpsy.spittr.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by drpsy on 17-Dec-17 (19:19).
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "org.drpsy.spittr.data.repositories")
@EnableTransactionManagement
public class DataSourceConfig {

  @Bean
  public DataSource dataSource() {
    JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
    return jndiDataSourceLookup.getDataSource("jdbc/MySQLSpittr");
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    LocalContainerEntityManagerFactoryBean lcem = new LocalContainerEntityManagerFactoryBean();
    lcem.setDataSource(dataSource());
    lcem.setPackagesToScan("org.drpsy.spittr.data.entities");
    lcem.setPersistenceUnitName("spittr");

    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    properties.setProperty("hibernate.show_sql", "true");
    properties.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
    properties.setProperty("hibernate.connection.charSet", "UTF-8");
    properties.setProperty("hibernate.validator.apply_to_ddl", "false");
    properties.setProperty("hibernate.validator.autoregister_listeners", "false");

    lcem.setJpaProperties(properties);

    return lcem;
  }

  /*
  PlatformTransactionManager interface, its implementations:
    JtaTransactionManager -----> JTA
    DataSourceTransactionManager -----> JDBC
    JpaTransactionManager ------> JPA
    HibernateTransactionManager ------> Hibernate
   */
  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return txManager;
  }

}
