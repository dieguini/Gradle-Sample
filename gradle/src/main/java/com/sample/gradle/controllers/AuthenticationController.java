package com.sample.gradle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.gradle.config.JwtUtils;
import com.sample.gradle.models.AuthenticationRequest;
import com.sample.gradle.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final UserDetails user = userRepository.findUserByEmail(request.getEmail());

        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }

        return ResponseEntity.status(400).body("Some error has occurred");
    }
}
