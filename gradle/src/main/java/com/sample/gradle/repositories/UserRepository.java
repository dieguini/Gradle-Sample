package com.sample.gradle.repositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  
  private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
      new User(
          "djauregui@mail.com",
          "$2a$10$sc7Xe3M0fml01Ebu7nm9ZOgohIgPTNsl6zpvgR3YJWfJ/Aq5dlhKG",
          Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));

  public UserDetails findUserByEmail(String email) {
    return APPLICATION_USERS
        .stream()
        .filter(u -> u.getUsername().equals(email))
        .findFirst()
        .orElseThrow(() -> new UsernameNotFoundException("No user was found"));
  }
}
