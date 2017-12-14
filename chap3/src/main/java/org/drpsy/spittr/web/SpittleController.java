package org.drpsy.spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;
import org.drpsy.spittr.Spittle;
import org.drpsy.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by drpsy on 11-Nov-17 (16:37).
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

  private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

  private SpittleRepository spittleRepository;

  @Autowired
  public SpittleController(SpittleRepository spittleRepository) {
    this.spittleRepository = spittleRepository;
  }

  @RequestMapping(method = GET)
  public String spittles(
      @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
      @RequestParam(value = "count", defaultValue = "20") int count,
      Model model) {
    model.addAttribute("spittleList", spittleRepository.findSpittles(max, count));
    return "spittles";
  }

  @RequestMapping(value = "/{spittleId}", method = GET)
  public String spittle(
      @PathVariable("spittleId") long spittleId, // PathVariable value can be omitted if the placeholder's name is the
      // same as the method parameter name.
      Model model) {
    Spittle spittle = spittleRepository.findOne(spittleId);
    if (spittle == null) {
      throw new SpittleNotFoundException();
    }

    model.addAttribute(spittle); // Model key will be spittle, inferred by the type passed in 'addAttribute'.
    return "spittle";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String saveSpittle(SpittleForm form, Spittle model) {
    spittleRepository.save(null, form.getMessage(), new Date(), form.getLongitude(), form.getLatitude());
    return "redirect:/spittles";
  }

  @ExceptionHandler(DuplicateSpittleException.class)
  public String handleDuplicateSpittle() {
    return "error/duplicate";
  }

}
