package com.elearningplatformservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.*;


@RestController
public class ResourceApplication {

  @RequestMapping("/")
  @CrossOrigin(origins = "*", maxAge = 3600,
    allowedHeaders = { "x-auth-token", "x-requested-with", "x-xsrf-token" })
  public Message home() {
    return new Message("Hello You");
  }

  @Bean
  HeaderHttpSessionStrategy sessionStrategy() {
    return new HeaderHttpSessionStrategy();
  }

}
