package org.drpsy.spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.drpsy.spittr.Spitter;
import org.drpsy.spittr.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by drpsy on 16-Nov-17 (22:44).
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

  private SpitterRepository spitterRepository;

  public SpitterController() {
  }

  @Autowired
  public SpitterController(SpitterRepository spitterRepository) {
    this.spitterRepository = spitterRepository;
  }

  @RequestMapping(value = "/register", method = GET)
  public String showRegistrationForm() {
    return "registerForm";
  }

  @RequestMapping(value = "/{userName}", method = GET)
  public String showSpitterPorfile(@PathVariable String userName, Model model) {
    Spitter spitter = spitterRepository.findByUserName(userName);
    model.addAttribute(spitter);
    return "profile";
  }

  @RequestMapping(value = "/register", method = POST)
  public String processRegistration(Spitter spitter) {
    spitterRepository.save(spitter);
    return "redirect:/spitter/" + spitter.getUserName();
  }

}