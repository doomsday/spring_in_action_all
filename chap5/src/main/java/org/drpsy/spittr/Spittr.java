package org.drpsy.spittr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.drpsy.spittr.validation.groups.StepOne;
import org.drpsy.spittr.validation.groups.StepTwo;

/**
 * Created by drpsy on 16-Nov-17 (23:19).
 */
@Entity
@Table(name = "users")
public class Spittr {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @NotNull(groups = StepOne.class)
  @Size(min = 5, max = 16, message = "{userName.size}", groups = StepOne.class)
  @Column(name = "username")
  private String userName;

  @NotNull(groups = StepOne.class)
  @Size(min = 5, max = 25, message = "{password.size}", groups = StepOne.class)
  private String password;

  @NotNull(groups = StepTwo.class)
  private Boolean enabled;

  @NotNull(groups = StepOne.class)
  @Size(min = 2, max = 30, message = "{firstName.size}", groups = StepOne.class)
  @Column(name = "firstname")
  private String firstName;

  @NotNull(groups = StepOne.class)
  @Size(min = 2, max = 30, message = "{lastName.size}", groups = StepOne.class)
  @Column(name = "lastname")
  private String lastName;

  @NotNull(groups = StepOne.class)
  @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\"
      + "x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]"
      + "*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25"
      + "[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-"
      + "\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "{email.valid}", groups = StepOne.class)
  private String email;

  @NotNull(groups = StepTwo.class)
  @Size(min = 36, max = 36, message = "{photoUUID.size}", groups = StepTwo.class)
  @Column(name = "photo_uuid")
  private String photoUUID;

  public Spittr() {}

  public Spittr(String userName, String password, String firstName, String lastName) {
    this(null, userName, password, firstName, lastName);
  }

  public Spittr(Long id, String userName, String password, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
  }

  public String getPhotoUUID() {
    return photoUUID;
  }

  public void setPhotoUUID(String photoUUID) {
    this.photoUUID = photoUUID;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Spittr spittr = (Spittr) o;

    return new EqualsBuilder()
        .append(firstName, spittr.firstName)
        .append(lastName, spittr.lastName)
        .append(userName, spittr.userName)
        .append(password, spittr.password)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(firstName)
        .append(lastName)
        .append(userName)
        .append(password)
        .toHashCode();
  }

}
