package org.drpsy.spittr;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by drpsy on 16-Nov-17 (23:19).
 */
public class Spitter {

  private Long id;

  @NotNull
  @Size(min = 2, max = 30, message = "{firstName.size}")
  private String firstName;

  @NotNull
  @Size(min = 2, max = 30, message = "{lastName.size}")
  private String lastName;

  @NotNull
  @Size(min = 5, max = 16, message = "{userName.size}")
  private String userName;

  @NotNull
  @Size(min = 5, max = 25, message = "{password.size}")
  private String password;

  @NotNull
  @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\"
      + "x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]"
      + "*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25"
      + "[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-"
      + "\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "{email.valid}")
  private String email;

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
