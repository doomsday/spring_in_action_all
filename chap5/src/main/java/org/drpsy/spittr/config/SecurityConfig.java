package org.drpsy.spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Any bean in the Spring application context that implements WebSecurityConfigurer
 * can contribute to Spring Security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // Demands that all HTTP requests coming into the application be authenticated.
        .authorizeRequests()
        .anyRequest().authenticated()
        // Support authentication via a form-based login (using a predefined login page) as well as HTTP Basic.
        .and().formLogin()
        .and().httpBasic();
  }

  // Build up authentication configuration.
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        // Enable an in-memory user store.
        .inMemoryAuthentication()
        // Add new users to the in-memory user store.
        .withUser("user").password("password").authorities("ROLE_USER").and()
        .withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
  }

}
