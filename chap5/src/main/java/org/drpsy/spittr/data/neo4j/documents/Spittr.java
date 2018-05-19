package org.drpsy.spittr.data.neo4j.documents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.drpsy.spittr.validation.groups.StepOne;
import org.drpsy.spittr.validation.groups.StepTwo;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Created by drpsy on 14-Jan-18 (12:04).
 */
@NodeEntity
public class Spittr implements Serializable {

  @Id @GeneratedValue
  private Long id;

  @NotNull(groups = StepOne.class)
  @Size(min = 5, max = 50, message = "{username.size}", groups = StepOne.class)
  private String username;

  @NotNull(groups = StepOne.class)
  @Size(min = 5, max = 50, message = "{password.size}", groups = StepOne.class)
  private String password;

  @NotNull(groups = StepOne.class)
  @Size(min = 2, max = 50, message = "{firstName.size}", groups = StepOne.class)
  private String firstName;

  @NotNull(groups = StepOne.class)
  @Size(min = 2, max = 50, message = "{lastName.size}", groups = StepOne.class)
  private String lastName;

  @NotNull(groups = StepOne.class)
  @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\"
      + "x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]"
      + "*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25"
      + "[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-"
      + "\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "{email.valid}", groups = StepOne.class)
  private String email;

  @NotNull(groups = StepTwo.class)
  private Boolean enabled = true;

  @NotNull(groups = StepTwo.class)
  private Boolean accountNonExpired = true;

  @NotNull(groups = StepTwo.class)
  private Boolean credentialsNonExpired = true;

  @NotNull(groups = StepTwo.class)
  private Boolean accountNonLocked = true;

  @NotNull(groups = StepTwo.class)
  @Size(min = 36, max = 36, message = "{photoUUID.size}", groups = StepTwo.class)
  private String photoUUID;

  private Set<String> roles = new HashSet<>(Collections.singleton("ROLE_SPITTR"));

  public Spittr() {
  }

  public Spittr(String username, String password, String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }

  public User getUser() {
    return new User(
        this.username,
        this.password,
        this.enabled,
        this.accountNonExpired,
        this.credentialsNonExpired,
        this.accountNonLocked,
        getAuthorities(this));
  }

  public Boolean getAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(Boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public Boolean getCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public Boolean getAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(Boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  public String getPhotoUUID() {
    return photoUUID;
  }

  public void setPhotoUUID(String photoUUID) {
    this.photoUUID = photoUUID;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
        .append(username, spittr.username)
        .append(password, spittr.password)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(firstName)
        .append(lastName)
        .append(username)
        .append(password)
        .toHashCode();
  }

  private List<GrantedAuthority> getAuthorities(Spittr spittr) {
    Set<String> roles = spittr.getRoles();
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(role));
    }
    return authorities;
  }

}