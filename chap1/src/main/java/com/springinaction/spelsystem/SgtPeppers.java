package com.springinaction.spelsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 30-Oct-17 (23:04).
 */
@Component
public class SgtPeppers {

  @Value("${disc.artist}")
  private String artist;

  public String getArtist() {
    return artist;
  }

}
