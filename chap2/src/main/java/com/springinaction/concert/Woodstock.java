package com.springinaction.concert;

import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 10-Nov-17 (15:04).
 */
@Component
public class Woodstock implements Performance {

  public void perform() {
    System.out.println("Woodstock performance");
  }
}
