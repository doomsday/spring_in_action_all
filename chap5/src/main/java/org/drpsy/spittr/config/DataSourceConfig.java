package org.drpsy.spittr.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * Created by drpsy on 17-Dec-17 (19:19).
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "org.drpsy.spittr.data.repositories",
    repositoryImplementationPostfix="Helper")
@EnableTransactionManagement
public class DataSourceConfig {

  // A factory for connections to the physical data source that this DataSource object represents.
  @Bean
  public DataSource dataSource() {
    JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
    
    return jndiDataSourceLookup.getDataSource("jdbc/MySQLSpittrDS");
  }

  // FactoryBean that creates a JPA EntityManagerFactory
  // according to JPA's standard container bootstrap contract.
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

    LocalContainerEntityManagerFactoryBean lcem = new LocalContainerEntityManagerFactoryBean();
    lcem.setDataSource(dataSource);
    lcem.setPackagesToScan("org.drpsy.spittr.data.entities");
    lcem.setJpaVendorAdapter(jpaVendorAdapter());
    lcem.setPersistenceUnitName("spittr");
    lcem.setJpaProperties(additionalProperties());

    return lcem;
  }

  // This is the central interface in Spring's transaction infrastructure. Applications can use this directly, but it
  // is not primarily meant as API: Typically, applications will work with either TransactionTemplate or declarative
  // transaction demarcation through AOP.
  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JtaTransactionManager();
  }

  // Translates native resource exceptions to Spring's DataAccessException hierarchy
  // to any bean marked with Spring's @Repository annotation.
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabase(Database.MYSQL);
    adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
    adapter.setShowSql(true);
    adapter.setGenerateDdl(false);

    return adapter;
  }

  // Processes PersistenceUnit and PersistenceContext annotations, for injection of the corresponding JPA resources.
  @Bean
  public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
    return new PersistenceAnnotationBeanPostProcessor();
  }

  private Properties additionalProperties() {
    Properties jpaProperties = new Properties();
    jpaProperties.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
    jpaProperties.setProperty("hibernate.connection.charSet", "UTF-8");
    jpaProperties.setProperty("hibernate.validator.apply_to_ddl", "false");
    jpaProperties.setProperty("hibernate.validator.autoregister_listeners", "false");

    return jpaProperties;
  }

}
