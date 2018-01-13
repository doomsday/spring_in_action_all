package org.drpsy.spittr.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.drpsy.spittr.data.entities.Spittr;
import org.drpsy.spittr.data.repositories.jpa.SpittrRepository;
import org.drpsy.spittr.web.controllers.SpittrController;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by drpsy on 16-Nov-17 (23:12).
 */
class SpittrControllerTest {

  @Test
  void shouldShowRegistration() throws Exception {
    SpittrController controller = new SpittrController();
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    mockMvc.perform(get("/spittr/register"))
        .andExpect(view().name("registerForm"));
  }

  @Test
  void shouldProcessRegistration() throws Exception {

    SpittrRepository mockRepository = mock(SpittrRepository.class);
    Spittr unsaved = new Spittr("jbauer", "24hours", "Jack", "Bauer");
    Spittr saved = new Spittr("jbauer", "24hours", "Jack", "Bauer");

    when(mockRepository.save(unsaved)).thenReturn(saved);

    SpittrController controller = new SpittrController();
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    mockMvc.perform(post("/spittr/register")
        .param("firstName", "Jack")
        .param("lastName", "Bauer")
        .param("userName", "jbauer")
        .param("password", "24hours"))
        .andExpect(redirectedUrl("/spittr/jbauer"));

    verify(mockRepository, atLeastOnce()).save(unsaved);

  }

}
