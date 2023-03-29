package com.sample.gradle.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DevOps")
public class DevOpsController {

  @PostMapping
  public ResponseEntity<Object> devops(@RequestBody com.sample.gradle.models.DevOpsModel devops) {
    String newMessage = "Hello "+ devops.getTo().trim()+" your message will be send";

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", newMessage);

    // Some business logic

    return new ResponseEntity<Object>(map, HttpStatus.OK);
  }
}
