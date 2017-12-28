package org.drpsy.spittr.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Any bean in the Spring application context that implements WebSecurityConfigurer
 * can contribute to Spring Security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final DataSource dataSource;

  @Autowired
  public SecurityConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Configuring authentication.
    http
        .formLogin()  // Authentication via a form-based login (using a predefined login page).
          .loginPage("/auth/login")

        .and()
        .logout() // Logout support.
        .logoutUrl("/auth/logout")
        .logoutSuccessUrl("/auth/login")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)

        .and()
        .httpBasic()  // HTTP Basic authentication.
          .realmName("Spittr")

        .and()
        .rememberMe() // Remember Me authentication.
          .key("spittrKey");

    // Configuring to selectively apply security to different URL paths.
    http
        .authorizeRequests()
        // Permitted.
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/spittr/register").permitAll()
        .antMatchers(HttpMethod.GET, "/spittr/auth/**").denyAll()
        .antMatchers(HttpMethod.POST,"/spittr/auth/**").permitAll()

        // Authenticated.
        .antMatchers("/spittles/**").authenticated()

        // Has role.
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/spittr/**").hasRole("SPITTR")

        // Everything else.
        .anyRequest().permitAll();

  }

  // Build up authentication configuration.
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(new BCryptPasswordEncoder());

  }

}
