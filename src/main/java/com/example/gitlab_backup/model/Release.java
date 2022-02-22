package com.example.gitlab_backup.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Release {

  private Tag tag;
  private String name;
  private String description;

  @Builder
  public Release(Tag tag, String name, String description) {
    this.tag = tag;
    this.name = name;
    this.description = description;
  }
}
