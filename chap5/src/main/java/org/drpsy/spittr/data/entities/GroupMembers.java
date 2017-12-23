package org.drpsy.spittr.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by drpsy on 17-Dec-17 (23:08).
 */
@Entity
@Table(name = "group_members")
public class GroupMembers {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  @Column(name = "group_id")
  private Integer groupId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
