package com.sample.gradle.models;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevOps {
  private String message;
  private String to;
  private String from;
  private Integer timeToLifeSec;
}
