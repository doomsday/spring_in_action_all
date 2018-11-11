package org.drpsy.spittr.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.drpsy.spittr.web.EmailForm;
import org.drpsy.spittr.web.services.SpitterEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by drpsy on 10-Nov-18 (03:54).
 */
@Controller
@RequestMapping("/email_send")
public class EmailController {

  @Autowired
  private SpitterEmailService emailService;

  @RequestMapping(method = GET)
  public String sendEmail(Model model) {
    model.addAttribute(new EmailForm());
    return "email_send";
  }

  @RequestMapping(value = "/send", method = POST)
  public String sendEmail(EmailForm emailForm) {
    emailService.sendSimpleSpittleEmail(emailForm);
    emailForm.setSenderAddress("_________@mail.com");
    return "redirect:/";
  }

}
