package com.sample.gradle.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sample.gradle.repositories.UserRepository;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtUtils jwtUtils;
  @Autowired
  private ApiKeyUtils apiKeyUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("X-JWT-KWY");
    final String apiKeyHeader = request.getHeader("X-Parse-REST-API-Key");
    final String userEmail;
    final String jwtToken;

    // Token
    if (authHeader == null) {
      filterChain.doFilter(request, response);
      return;
    }
    // Api Key
    if (apiKeyHeader == null) {
      filterChain.doFilter(request, response);
      return;
    }

    // jwtToken = authHeader.substring(7);
    jwtToken = authHeader;
    userEmail = jwtUtils.extractUsername(jwtToken);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userRepository.findUserByEmail(userEmail);
      if (jwtUtils.isTokenValid(jwtToken, userDetails) && apiKeyUtils.isApiKeyValid(apiKeyHeader)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }

}
