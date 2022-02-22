package com.example.gitlab_backup.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

  private static Long USER_ID = 0L;

  private final Long id;
  private String apiToken;
  private String name;

  @Builder
  public User(String apiToken, String name) {
    this.id = USER_ID++;
    this.apiToken = apiToken;
    this.name = name;
  }
}
