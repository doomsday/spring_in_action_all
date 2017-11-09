package com.springinaction.spelsystem;

import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 31-Oct-17 (23:12).
 */
@Component
public class Email {

  private String email = "hello@world.org";

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
