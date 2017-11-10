package com.springinaction.aspects.introducers;

/**
 * Created by drpsy on 10-Nov-17 (15:08).
 */
public class EncoreableDelegate implements Encoreable {

  @Override
  public void performEncore() {
    System.out.println("Performing default encore");
  }
}
