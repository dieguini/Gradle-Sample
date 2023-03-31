package com.sample.gradle.models.DevOpsModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.gradle.models.DevOpsModel;

@SpringBootTest
public class DevOpsModelGetterSetterTest {
  private DevOpsModel devOpsModel;

  @BeforeEach
  public void setUp() {
    devOpsModel = new DevOpsModel("Hello", "John", "Jane", 60);
  }

  @Test
  public void Should_GetData_When_Needed() {
    assertEquals("Hello", devOpsModel.getMessage());
    assertEquals("John", devOpsModel.getTo());
    assertEquals("Jane", devOpsModel.getFrom());
    assertEquals(60, devOpsModel.getTimeToLifeSec());
  }

  @Test
  public void Should_SetData_When_Needed() {
    devOpsModel.setMessage("Hi");
    devOpsModel.setTo("Mary");
    devOpsModel.setFrom("Jack");
    devOpsModel.setTimeToLifeSec(120);
    assertEquals("Hi", devOpsModel.getMessage());
    assertEquals("Mary", devOpsModel.getTo());
    assertEquals("Jack", devOpsModel.getFrom());
    assertEquals(120, devOpsModel.getTimeToLifeSec());
  }
}
