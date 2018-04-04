package org.drpsy.spittr.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.transaction.Transactional;
import org.drpsy.spittr.data.neo4j.documents.Spittr;
import org.drpsy.spittr.data.repositories.neo4j.SpittrNeo4jRepository;
import org.drpsy.spittr.validation.groups.StepOne;
import org.drpsy.spittr.web.exceptions.DuplicateSpittrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
  private Environment env;

  @Autowired
  private SpittrNeo4jRepository spittrRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // GET /spittr/{username}
  @RequestMapping(value = "/{username}", method = GET)
  public String showSpittrProfile(@PathVariable String username, Model model) {
    if (!model.containsAttribute("spittr")) {
      model.addAttribute(spittrRepository.findByUsername(username));
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

    if (spittrRepository.findByUsername(spittr.getUsername()) != null) {
      throw new DuplicateSpittrException();
    }

    // Encrypt password.
    spittr.setPassword(passwordEncoder.encode(spittr.getPassword()));

    // Photo processing.
    String photoSaveDir = env.getProperty("photo.save.dir", System.getProperty("java.io.tmpdir"));
    if (!profilePicture.isEmpty()) {
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
    model.addAttribute("username", spittr.getUsername());

    // Sending actual Spittr object to redirect page.
    model.addFlashAttribute("spittr", spittr);

    return "redirect:/spittr/{username}";
  }

}
