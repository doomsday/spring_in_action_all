package org.drpsy.spittr.data.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by drpsy on 11-Nov-17 (13:56).
 */
public class Spittle {

  private Long id;

  private String message;

  private Date time;

  private Double latitude;

  private Double longitude;

  public Spittle() {
  }

  public Spittle(String message, Date time) {
    this(message, time, null, null);
  }

  public Spittle(String message, Date time, Double longitude, Double latitude) {
    this(null, message, time, longitude, latitude);
  }

  public Spittle(Long id, String message, Date time, Double longitude, Double latitude) {
    this.id = id;
    this.message = message;
    this.time = time;
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Spittle spittle = (Spittle) o;

    return new EqualsBuilder()
        .append(message, spittle.message)
        .append(time, spittle.time)
        .append(latitude, spittle.latitude)
        .append(longitude, spittle.longitude)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(message)
        .append(time)
        .append(latitude)
        .append(longitude)
        .toHashCode();
  }
}
