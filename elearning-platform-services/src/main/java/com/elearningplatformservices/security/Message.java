package com.elearningplatformservices.security;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
class Message {

  private String id = UUID.randomUUID().toString();
  private String content;

  Message(String content) {
    this.content = content;
  }

  public Message() {
  }
}
