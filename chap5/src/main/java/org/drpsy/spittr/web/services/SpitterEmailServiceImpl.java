package org.drpsy.spittr.web.services;

import org.drpsy.spittr.web.EmailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 05-Nov-18 (01:26).
 */
@Component
public class SpitterEmailServiceImpl implements SpitterEmailService {

  @Autowired
  private MailSender mailSender;

  @Override
  public void sendSimpleSpittleEmail(EmailForm emailForm) {
    SimpleMailMessage message = new SimpleMailMessage();
    String spitterName = "Test name";
    message.setFrom(emailForm.getSenderAddress());
    message.setTo(emailForm.getDestAddress());
    message.setSubject("New spittle from " + spitterName);
    message.setText(spitterName + " says: " + emailForm.getDestAddress());
    mailSender.send(message);
  }

}
