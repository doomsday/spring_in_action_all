package com.springinaction.spelsystem;

import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 31-Oct-17 (22:35).
 */
@Component
public class Circle {

  private Double radius = 125.3;

  public Double getRadius() {
    return radius;
  }

  public void setRadius(Double radius) {
    this.radius = radius;
  }

}
