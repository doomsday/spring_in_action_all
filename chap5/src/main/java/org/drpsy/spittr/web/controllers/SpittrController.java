package org.drpsy.spittr.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.drpsy.spittr.config.PropertiesConfigReader;
import org.drpsy.spittr.data.entities.Spittr;
import org.drpsy.spittr.data.repositories.SpittrRepository;
import org.drpsy.spittr.validation.groups.StepOne;
import org.drpsy.spittr.web.exceptions.DuplicateSpittrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by drpsy on 16-Nov-17 (22:44).
 */
@Controller
@RequestMapping("/spittr")
public class SpittrController {

  @Autowired
  private SpittrRepository spittrRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private PropertiesConfigReader configReader;

  // GET /spittr/{userName}
  @RequestMapping(value = "/{userName}", method = GET)
  public String showSpittrProfile(@PathVariable String userName, Model model) {
    if (!model.containsAttribute("spittr")) {
      model.addAttribute(spittrRepository.findByUserName(userName));
    }
    return "profile";
  }

  // GET /spittr/register
  @RequestMapping(value = "/register", method = GET)
  public String showRegistrationForm(Model model) {
    model.addAttribute(new Spittr());  // Model key will be inferred from the object type: "spittr".
    return "registerForm";
  }

  // This object has firstName, lastName, username, and password properties that
  // will be populated from the request parameters of the same name.
  //
  // POST /spittr/register
  @Transactional
  @RequestMapping(value = "/register", method = POST)
  public String processRegistration(
      RedirectAttributes model,                 // Sub-interface of Model with methods for setting flash attributes.
      @RequestParam("profilePicture") MultipartFile profilePicture,
      @Validated(StepOne.class) Spittr spittr,  // Indicate to Spring that the command obj. has valid. constraints that
      // should be enforced.
      Errors errors)                            // If there are any validation errors, they're available in the
  // Errors object.
      throws IOException {

    // Validation.
    if (errors.hasErrors()) {
      return "registerForm";  // Return back to the form on validation errors.
    }

    if (spittrRepository.findByUserName(spittr.getUserName()) != null) {
      throw new DuplicateSpittrException();
    }

    // Encrypt password.
    spittr.setPassword(passwordEncoder.encode(spittr.getPassword()));

    // Photo processing.
    Optional<String> photoSaveDir = configReader.getPropValue("photo.save.dir");
    if (!profilePicture.isEmpty() && photoSaveDir.isPresent()) {
      // {tmpdir}/spittr/uploads/data/spittr
      String uuid = UUID.randomUUID().toString();
      profilePicture.transferTo(new File(photoSaveDir + "/" + uuid));
      spittr.setPhotoUUID(uuid);
    } else {
      spittr.setPhotoUUID("00000000-0000-0000-0000-000000000000");
    }

    // Enabled processing.
    spittr.setEnabled(true);

    // Persisting user.
    spittrRepository.save(spittr);

    // Preparing data for redirect URL template.
    model.addAttribute("username", spittr.getUserName());

    // Sending actual Spittr object to redirect page.
    model.addFlashAttribute("spittr", spittr);

    return "redirect:/spittr/{username}";
  }

}
