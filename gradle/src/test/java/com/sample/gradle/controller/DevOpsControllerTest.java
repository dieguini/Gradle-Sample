package com.sample.gradle.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.gradle.GradleApplication;
import com.sample.gradle.controllers.AuthenticationController;
import com.sample.gradle.controllers.DevOpsController;
import com.sample.gradle.models.DevOpsModel;

import static org.assertj.core.api.Assertions.assertThat;

// @WebMvcTest(DevOpsController.class)
@ContextConfiguration(classes=GradleApplication.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DevOpsControllerTest {

  ObjectMapper objectMapper = new ObjectMapper();
  
  // @Autowired
  private MockMvc mvc;
  @Autowired
  WebApplicationContext webApplicationContext;
  @Autowired
  DevOpsController devOpsController;
  @Autowired
  AuthenticationController authenticationController;

  @BeforeEach
  protected void setUp() {
    mvc = MockMvcBuilders
          .webAppContextSetup(webApplicationContext)
          .build();
  }

  @Test
	public void contextLoads() throws Exception {
		assertThat(devOpsController).isNotNull();
		assertThat(authenticationController).isNotNull();
	}

  @Test
  public void Should_ThrowOkAndJsonResponse_When_DataCorrect() throws Exception{
    String uri = "/DevOps";
    String toName = "juan";

    DevOpsModel devOpsModel = new DevOpsModel("message", toName,"pablo", 44);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(devOpsModel))
      ).andReturn();
    int status = mvcResult.getResponse().getStatus();
    String response = mvcResult.getResponse().getContentAsString();

    assertEquals(status, 200);
    assertEquals(response, "{\"message\":\"Hello "+toName+" your message will be send\"}");
  }
}
