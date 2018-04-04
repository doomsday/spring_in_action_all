package org.drpsy.spittr.config;

import org.drpsy.spittr.data.neo4j.documents.Spittr;
import org.drpsy.spittr.data.repositories.neo4j.UserNeo4jRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 14-Jan-18 (12:31).
 */
@Component
public class SecUserDetailsService implements UserDetailsService {

  @Autowired
  private UserNeo4jRepository userNeo4jRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    // Fetching users from the MongoDB
    Spittr spittr = userNeo4jRepository.findByUsername(username);

    if (spittr == null) {
      throw new UsernameNotFoundException(username);
    } else {
      return spittr.getUser();
    }

  }

}
