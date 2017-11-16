package org.drpsy.spittr.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.drpsy.spittr.Spitter;
import org.drpsy.spittr.data.SpitterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by drpsy on 16-Nov-17 (23:12).
 */
class SpitterControllerTest {

  @Test
  void shouldShowRegistration() throws Exception {
    SpitterController controller = new SpitterController();
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    mockMvc.perform(get("/spitter/register"))
        .andExpect(view().name("registerForm"));
  }

  @Test
  void shouldProcessRegistration() throws Exception {

    SpitterRepository mockRepository = mock(SpitterRepository.class);
    Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer");
    Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer");

    when(mockRepository.save(unsaved)).thenReturn(saved);

    SpitterController controller = new SpitterController(mockRepository);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    mockMvc.perform(post("/spitter/register")
        .param("firstName", "Jack")
        .param("lastName", "Bauer")
        .param("userName", "jbauer")
        .param("password", "24hours"))
        .andExpect(redirectedUrl("/spitter/jbauer"));

    verify(mockRepository, atLeastOnce()).save(unsaved);

  }

}
