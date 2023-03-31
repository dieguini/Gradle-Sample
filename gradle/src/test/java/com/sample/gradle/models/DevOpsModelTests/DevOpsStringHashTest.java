package com.sample.gradle.models.DevOpsModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.gradle.models.DevOpsModel;

@SpringBootTest
public class DevOpsStringHashTest {
  private DevOpsModel devOpsModel1;
  private DevOpsModel devOpsModel2;

  @BeforeEach
  public void setUp() {
    devOpsModel1 = new DevOpsModel("Hello", "John", "Jane", 60);
    devOpsModel2 = new DevOpsModel("Hello", "John", "Jane", 60);
  }

  @Test
  public void testToString() {
    String expectedString = "DevOpsModel(message=Hello, to=John, from=Jane, timeToLifeSec=60)";
    assertEquals(expectedString, devOpsModel1.toString());
  }

  @Test
  public void testHashCodeEqualObjects() {
    assertEquals(devOpsModel1.hashCode(), devOpsModel2.hashCode());
  }
}
