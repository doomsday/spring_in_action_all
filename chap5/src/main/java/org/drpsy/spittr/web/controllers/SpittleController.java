package org.drpsy.spittr.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;
import java.util.Optional;
import org.drpsy.spittr.data.neo4j.documents.Spittle;
import org.drpsy.spittr.data.neo4j.documents.Spittr;
import org.drpsy.spittr.data.repositories.neo4j.SpittleNeo4jRepositoryWrapper;
import org.drpsy.spittr.data.repositories.neo4j.SpittrNeo4jRepository;
import org.drpsy.spittr.web.SpittleForm;
import org.drpsy.spittr.web.exceptions.SpittleNotFoundException;
import org.drpsy.spittr.web.services.SpittleFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// JPA version
//import org.drpsy.spittr.data.repositories.jpa.SpittleRepository;
//import org.drpsy.spittr.data.entities.Spittle;
// MongoDB version

/**
 * Created by drpsy on 11-Nov-17 (16:37).
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

  @Autowired
  private SpittleFeedService spittleFeedService;

  @Autowired
  private SpittleNeo4jRepositoryWrapper spittleRepository;

  @Autowired
  private SpittrNeo4jRepository spittrRepository;

  // GET /spittles
  @RequestMapping(method = GET)
  public String spittles(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "count", defaultValue = "20") int count,
      Model model) {

    Page<Spittle> spittles = spittleRepository.findAll(PageRequest.of(page, count));

    model.addAttribute("spittleList", spittles.getContent());
    model.addAttribute("spittle", new Spittle());
    return "spittles";
  }

  // GET /spittles/1
  @RequestMapping(value = "/{spittleId}", method = GET)
  public String spittle(
      @PathVariable("spittleId") Long spittleId, // PathVariable value can be omitted if the placeholder's name is the
      // same as the method parameter name.
      Model model) {

    Optional<Spittle> spittle = spittleRepository.findById(spittleId);

    if (!spittle.isPresent()) {
      throw new SpittleNotFoundException();
    } else {
      model.addAttribute(spittle.get()); // Model key will be spittle, inferred by the type passed in 'addAttribute'.
    }
    
    return "spittle";
  }

  // POST /spittles
  @RequestMapping(method = RequestMethod.POST)
  public String saveSpittle(SpittleForm form) {
    UserDetails currentUserDetails
        = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String username = currentUserDetails.getUsername();
    Spittr currentSpittr = spittrRepository.findByUsername(username);

    if (currentSpittr == null) {
      throw new UsernameNotFoundException(username);
    }

    Spittle spittle = new Spittle(form.getMessage(), new Date(), form.getLongitude(), form.getLatitude(),
        currentSpittr);

    spittleRepository.save(spittle);
    spittleFeedService.broadcastSpittle(spittle);

    return "redirect:/spittles";
  }

  // DELETE /spittles/1
  @RequestMapping(value = "/delete/{spittleId}", method = RequestMethod.DELETE)
  public String delete(@PathVariable Long spittleId) {

    Optional<Spittle> spittle = spittleRepository.findById(spittleId);
    if(!spittle.isPresent()) {
      throw new SpittleNotFoundException();
    } else {
      spittleRepository.delete(spittle.get());
    }

    return "redirect:/spittles";
  }

}
