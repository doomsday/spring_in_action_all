package org.drpsy.spittr.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by drpsy on 17-Dec-17 (23:09).
 */
@Entity
public class Groups {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "group_name")
  private Integer groupName;

  public Integer getGroupName() {
    return groupName;
  }

  public void setGroupName(Integer groupName) {
    this.groupName = groupName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
