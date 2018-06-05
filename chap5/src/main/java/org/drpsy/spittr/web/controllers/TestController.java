package org.drpsy.spittr.web.controllers;

import org.drpsy.spittr.service.SpittrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by drpsy on 05-Jun-18 (00:22).
 */
@Controller
@RequestMapping("test")
public class TestController {

  @Autowired
  private SpittrService spittrService;

  @RequestMapping("hessian")
  public String hessianTest(Model model) {
    model.addAttribute(spittrService.findByUsername("username"));
    return "test_pages/hessian_test";
  }

}
