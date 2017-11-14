package org.drpsy.spittr.web;

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

}
