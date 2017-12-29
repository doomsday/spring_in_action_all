package org.drpsy.spittr.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by drpsy on 17-Dec-17 (23:05).
 */
@Entity
@Table(name = "group_authorities")
public class GroupAuthorities {

  @Id
  @Column(name = "group_id")
  private Integer groupId;

  private String authority;

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

}
