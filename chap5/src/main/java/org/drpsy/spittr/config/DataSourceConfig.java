package org.drpsy.spittr.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * Created by drpsy on 17-Dec-17 (19:19).
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

  // A factory for connections to the physical data source that this DataSource object represents.
  @Bean
  public DataSource dataSource() {
    JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
    return jndiDataSourceLookup.getDataSource("jdbc/MySQLSpittr");
  }

  @Bean
  public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
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

}
