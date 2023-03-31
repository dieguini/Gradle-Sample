package com.sample.gradle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer {
  private final JwtAuthFilter jwtAuthFilter;

  @Autowired
  private UserDetailsService jwtUserDetailsService;

  @Qualifier("customAuthenticationEntryPoint")
  @Autowired
  AuthenticationEntryPoint authEntryPoint;
  
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      // Enable CORS, default basic and disable CSRF
      http
              .cors(withDefaults())
              .httpBasic(withDefaults())
              .csrf(csrf -> csrf.disable());
    // Set session management to stateless
    http
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and());

    http
        .exceptionHandling(except -> except
            .authenticationEntryPoint(authEntryPoint)
            .and());
    
    // Set permissions on endpoints
    http
        .authorizeHttpRequests(authorize -> authorize
            // Public endpoints
            .antMatchers("/**/auth/**").permitAll()
            // Private endpoints
            .anyRequest().authenticated());

    // Add JWT token filter
    http.addFilterBefore(
        jwtAuthFilter,
        UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return this.jwtUserDetailsService;
  }
}
