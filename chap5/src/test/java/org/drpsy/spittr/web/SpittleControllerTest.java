//package org.drpsy.spittr.web;
//
//import static org.hamcrest.CoreMatchers.hasItems;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.drpsy.spittr.Spittle;
//import org.drpsy.spittr.data.repositories.SpittleRepository;
//import org.drpsy.spittr.web.controllers.SpittleController;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.view.InternalResourceView;
//
///**
// * Created by drpsy on 11-Nov-17 (10:14).
// */
//public class SpittleControllerTest {
//
//  @Test
//  public void shouldShowRecentSpittles() throws Exception {
//    List<Spittle> expectedSpittles = createSpittleList(20);
//    SpittleRepository mockRepository = mock(SpittleRepository.class);
//    when(mockRepository.findSpittles(Long.MAX_VALUE, 20))
//        .thenReturn(expectedSpittles);
//
//    SpittleController controller = new SpittleController(mockRepository);
//    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
//        .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
//        .build();
//
//    mockMvc.perform(get("/spittles"))
//        .andExpect(view().name("spittles"))
//        .andExpect(model().attributeExists("spittleList"))
//        .andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
//  }
//
//  @Test
//  public void shouldShowPagedSpittles() throws Exception {
//    List<Spittle> expectedSpittles = createSpittleList(50);
//    SpittleRepository mockRepository = mock(SpittleRepository.class);
//    when(mockRepository.findSpittles(238900, 50)) // Expect max and count parameters.
//        .thenReturn(expectedSpittles);
//
//    SpittleController controller = new SpittleController(mockRepository);
//    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
//        .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
//        .build();
//
//    mockMvc.perform(get("/spittles?max=238900&count=50"))  // Pass max and count parameters.
//        .andExpect(view().name("spittles"))
//        .andExpect(model().attributeExists("spittleList"))
//        .andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
//  }
//
//  @Test
//  public void testSpittle() throws Exception {
//    Spittle expectedSpittle = new Spittle("Hello", new Date());
//    SpittleRepository mockRepository = mock(SpittleRepository.class);
//    when(mockRepository.findOne(123456)).thenReturn(expectedSpittle);
//
//    SpittleController controller = new SpittleController(mockRepository);
//    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//
//    mockMvc.perform(get("/spittles/123456"))
//        .andExpect(view().name("spittle"))
//        .andExpect(model().attributeExists("spittle"))
//        .andExpect(model().attribute("spittle", expectedSpittle));
//  }
//
//  private List<Spittle> createSpittleList(int count) {
//    List<Spittle> spittles = new ArrayList<>();
//    for (int i = 0; i < count; i++) {
//      spittles.add(new Spittle("Spittle " + i, new Date()));
//    }
//    return spittles;
//  }
//
//}
