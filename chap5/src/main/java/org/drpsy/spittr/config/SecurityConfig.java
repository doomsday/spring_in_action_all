package org.drpsy.spittr.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Any bean in the Spring application context that implements WebSecurityConfigurer
 * can contribute to Spring Security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        // Support authentication via a form-based login (using a predefined login page) as well as HTTP Basic.
        .formLogin()
        .and().httpBasic();

    http
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/**").authenticated()  // Should be authenticated.
        .anyRequest().permitAll();

  }

  // Build up authentication configuration.
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(new BCryptPasswordEncoder());

    // We can configure our own auth queries. All of them take the username as their only parameter.
    /*
        .usersByUsernameQuery(
            "SELECT username, password, true " +
                "FROM spittr WHERE username = ?")
        .authoritiesByUsernameQuery(
            "SELECT username, 'ROLE_USER' "
                + "FROM spittr WHERE username = ?");
     */
  }

}
