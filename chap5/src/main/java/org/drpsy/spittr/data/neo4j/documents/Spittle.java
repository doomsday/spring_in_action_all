package org.drpsy.spittr.data.neo4j.documents;

import java.io.Serializable;
import java.util.Date;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * Created by drpsy on 13-Jan-18 (22:45).
 */

@NodeEntity
public class Spittle implements Serializable {

  @Id @GeneratedValue
  private Long id;

  private String message;

  @DateLong
  private Date time;

  private Double latitude;

  private Double longitude;

  @Relationship
  private Spittr author;

  public Spittle() {}

  @PersistenceConstructor
  public Spittle(String message, Date time, Double longitude, Double latitude, Spittr author) {
    this.message = message;
    this.time = time;
    this.setLongitude(longitude);
    this.setLatitude(latitude);
    this.setAuthor(author);
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

  public Spittr getAuthor() {
    return author;
  }

  public void setAuthor(Spittr author) {
    this.author = author;
  }
}
