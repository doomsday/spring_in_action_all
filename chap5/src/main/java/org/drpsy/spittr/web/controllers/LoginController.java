package org.drpsy.spittr.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by drpsy on 23-Dec-17 (07:47).
 */
@Controller
@RequestMapping("/login")
public class LoginController {

  @RequestMapping(method = GET)
  public String login() {
    return "login";
  }

}
