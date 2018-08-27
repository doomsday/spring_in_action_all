package org.drpsy.spittr.data.mongo.documents;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by drpsy on 13-Jan-18 (22:45).
 */

@Document(collection = "spittle")
public class Spittle implements Serializable {

  @Id
  private ObjectId id;

  private String message;

  private Date time;

  private Double latitude;

  private Double longitude;

  public Spittle() {}

  @PersistenceConstructor
  public Spittle(String message, Date time) {
    this(message, time, null, null);
  }

  @PersistenceConstructor
  public Spittle(String message, Date time, Double longitude, Double latitude) {
    this.message = message;
    this.time = time;
    this.setLongitude(longitude);
    this.setLatitude(latitude);
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
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
}
