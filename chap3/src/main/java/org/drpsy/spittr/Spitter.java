package org.drpsy.spittr;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by drpsy on 16-Nov-17 (23:19).
 */
public class Spitter {

  private Long id;

  @NotNull
  @Size(min = 2, max = 30)
  private String firstName;

  @NotNull
  @Size(min = 2, max = 30)
  private String lastName;

  @NotNull
  @Size(min = 5, max = 16)
  private String userName;

  @NotNull
  @Size(min = 5, max = 25)
  private String password;

  public Spitter() {}

  public Spitter(String userName, String password, String firstName, String lastName) {
    this(null, userName, password, firstName, lastName);
  }

  public Spitter(Long id, String userName, String password, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Spitter spitter = (Spitter) o;

    return new EqualsBuilder()
        .append(firstName, spitter.firstName)
        .append(lastName, spitter.lastName)
        .append(userName, spitter.userName)
        .append(password, spitter.password)
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
