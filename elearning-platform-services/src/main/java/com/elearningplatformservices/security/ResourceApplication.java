package com.elearningplatformservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;


@RestController
public class ResourceApplication {

  @RequestMapping("/")
  @CrossOrigin(origins = "*", maxAge = 3600,
    allowedHeaders = { "x-auth-token", "x-requested-with", "x-xsrf-token" })
  public Message home() {
    return new Message("Hello You");
  }

  @RequestMapping("/token")
  public Map<String,String> token(HttpSession session) {
    return Collections.singletonMap("token", session.getId());
  }

  @Bean
  HeaderHttpSessionStrategy sessionStrategy() {
    return new HeaderHttpSessionStrategy();
  }

}
