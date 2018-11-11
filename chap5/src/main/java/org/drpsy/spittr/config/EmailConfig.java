package org.drpsy.spittr.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by drpsy on 04-Nov-18 (18:17).
 */
@Configuration
public class EmailConfig {

  @Bean
  public MailSender mailSender(Environment env) {
    String port_ = env.getProperty("mail.server.port");
    int port = (port_ != null ? Integer.parseInt(port_) : 465);

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(env.getProperty("mail.server.host"));
    mailSender.setPort(port);
    mailSender.setUsername(env.getProperty("mail.server.username"));
    mailSender.setPassword(env.getProperty("mail.server.password"));

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.debug", "true");

    return mailSender;
  }

}
