package com.awesome.pizza.gestione_ordini;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AcceptanceTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mvc;
  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  protected ResultActions post(String uri, Object body) throws Exception {
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri)
        .contentType("application/json;charset=UTF-8")
        .content(objectMapper.writeValueAsString(body));
    return mvc.perform(request);
  }
  protected ResultActions get(String uri, Object... uriArgs) throws Exception {
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri, uriArgs)
        .accept("application/json;charset=UTF-8");
    return mvc.perform(request);
  }
}
