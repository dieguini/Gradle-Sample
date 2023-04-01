package com.sample.gradle.models.AuthenticationRequestModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.gradle.models.AuthenticationRequest;

@SpringBootTest
public class AuthenticationRequestTest {
  @Test
  public void Should_testAuthenticationRequest() {
      AuthenticationRequest request = new AuthenticationRequest();
      assertNotNull(request);
      request.setEmail("test@example.com");
      request.setPassword("password123");
      assertEquals("test@example.com", request.getEmail());
      assertEquals("password123", request.getPassword());
  }
}
