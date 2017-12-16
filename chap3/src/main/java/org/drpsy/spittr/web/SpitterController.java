package org.drpsy.spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.IOException;
import javax.validation.Valid;
import org.drpsy.spittr.Spitter;
import org.drpsy.spittr.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

  @RequestMapping(value = "/{userName}", method = GET)
  public String showSpitterProfile(@PathVariable String userName, Model model) {
    if (!model.containsAttribute("spitter")) {
      model.addAttribute(spitterRepository.findByUserName(userName));
    }
    return "profile";
  }

  @RequestMapping(value = "/register", method = GET)
  public String showRegistrationForm(Model model) {
    model.addAttribute(new Spitter());  // Model key will be inferred from the object type: "spitter".
    return "registerForm";
  }

  // This object has firstName, lastName, username, and password properties that
  // will be populated from the request parameters of the same name.
  @RequestMapping(value = "/register", method = POST)
  public String processRegistration(
      RedirectAttributes model,
      @RequestParam("profilePicture") MultipartFile profilePicture,
      @Valid Spitter spitter,
      Errors errors) throws IOException {
    if (errors.hasErrors()) {
      return "registerForm";  // Return back to the form on validation errors.
    }
    if (!profilePicture.isEmpty()) {
      // {tmpdir}/spittr/uploads/data/spittr
      profilePicture.transferTo(new File("/data/spittr/" + profilePicture.getOriginalFilename()));
    }
    spitterRepository.save(spitter);
    model.addAttribute("username", spitter.getUserName());
    model.addFlashAttribute("spitter", spitter);
    return "redirect:/spitter/{username}";
  }

}
