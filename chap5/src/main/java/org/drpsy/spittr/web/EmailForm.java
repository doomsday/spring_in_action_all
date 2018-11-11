package org.drpsy.spittr.web;

import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 10-Nov-18 (04:01).
 */
@Component
public class EmailForm {

  private String emailMessage;
  private String destAddress;
  private String senderAddress;

  public String getEmailMessage() {
    return emailMessage;
  }

  public void setEmailMessage(String emailMessage) {
    this.emailMessage = emailMessage;
  }

  public String getDestAddress() {
    return destAddress;
  }

  public void setDestAddress(String destAddress) {
    this.destAddress = destAddress;
  }


  public String getSenderAddress() {
    return senderAddress;
  }

  public void setSenderAddress(String senderAddress) {
    this.senderAddress = senderAddress;
  }
}
