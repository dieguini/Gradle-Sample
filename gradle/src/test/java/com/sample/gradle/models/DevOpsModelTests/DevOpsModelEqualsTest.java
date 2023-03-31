package com.sample.gradle.models.DevOpsModelTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.gradle.models.DevOpsModel;

@SpringBootTest
public class DevOpsModelEqualsTest {
  private DevOpsModel devOpsModel1;
  private DevOpsModel devOpsModel2;

  @BeforeEach
  public void setUp() {
    devOpsModel1 = new DevOpsModel("Hello", "John", "Jane", 60);
    devOpsModel2 = new DevOpsModel("Hello", "John", "Jane", 60);
  }

  @Test
  public void testEqualsSameObject() {
    assertTrue(devOpsModel1.equals(devOpsModel1));
  }

  @Test
  public void testEqualsEqualObjects() {
    assertTrue(devOpsModel1.equals(devOpsModel2));
  }

  @Test
  public void testEqualsDifferentObjects() {
    DevOpsModel devOpsModel3 = new DevOpsModel("Hello", "Mary", "Jane", 60);
    assertFalse(devOpsModel1.equals(devOpsModel3));
  }

  @Test
  public void testEqualsNullObject() {
    assertFalse(devOpsModel1.equals(null));
  }

  @Test
  public void testEqualsDifferentClass() {
    assertFalse(devOpsModel1.equals("Hello"));
  }

  @Test
  public void testEqualsDifferentMessage() {
    DevOpsModel devOpsModel3 = new DevOpsModel("Hi", "John", "Jane", 60);
    assertFalse(devOpsModel1.equals(devOpsModel3));
  }

  @Test
  public void testEqualsDifferentTo() {
    DevOpsModel devOpsModel3 = new DevOpsModel("Hello", "Mary", "Jane", 60);
    assertFalse(devOpsModel1.equals(devOpsModel3));
  }

  @Test
  public void testEqualsDifferentFrom() {
    DevOpsModel devOpsModel3 = new DevOpsModel("Hello", "John", "Alice", 60);
    assertFalse(devOpsModel1.equals(devOpsModel3));
  }

  @Test
  public void testEqualsDifferentTimeToLifeSec() {
    DevOpsModel devOpsModel3 = new DevOpsModel("Hello", "John", "Jane", 120);
    assertFalse(devOpsModel1.equals(devOpsModel3));
  }
}
