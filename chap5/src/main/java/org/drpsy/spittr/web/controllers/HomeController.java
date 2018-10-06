package org.drpsy.spittr.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by drpsy on 10-Nov-17 (22:28).
 */
@Controller // Could be annotated @Component and would have the save effect.
@RequestMapping({"/", "/homepage"})
public class HomeController {

  @RequestMapping(method = GET)
  public String home() {
    return "home";
  }

  @RequestMapping(value = "/chat_socket", method = GET)
  public String chat_socket() {
    return "chat_socket";
  }

  @RequestMapping(value = "/chat_stomp", method = GET)
  public String stomp_chat() {
    return "chat_stomp";
  }

}
