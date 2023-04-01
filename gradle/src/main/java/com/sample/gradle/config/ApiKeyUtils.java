package com.sample.gradle.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyUtils implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Value("${app.api_key}")
  private String apiKeyConf;

  public Boolean isApiKeyValid(String apiKey) {
    return (apiKey.equals(apiKeyConf));
  }
}
