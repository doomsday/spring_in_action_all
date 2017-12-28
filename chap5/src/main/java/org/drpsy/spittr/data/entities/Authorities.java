package org.drpsy.spittr.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by drpsy on 17-Dec-17 (23:02).
 */
@Entity
public class Authorities {

  @Id
  private String username;

  private String authority;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authoriry) {
    this.authority = authoriry;
  }

}
