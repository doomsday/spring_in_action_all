package org.drpsy.spittr.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.drpsy.spittr.web.controllers.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by drpsy on 16-Nov-17 (23:14).
 */
public class HomeControllerTest {

  @Test
  public void testHomePage() throws Exception {
    HomeController controller = new HomeController();
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    mockMvc.perform(get("/")).andExpect(view().name("home"));
  }

}
