package org.drpsy.spittr.data;

/**
 * Created by drpsy on 05-Oct-18 (01:41).
 */
public class Shout {

  private String message;

  public Shout() {}

  public Shout(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
