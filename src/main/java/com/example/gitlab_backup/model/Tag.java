package com.example.gitlab_backup.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
public class Tag {

  private final Long projectId;
  private final String tagName;
  private final String ref;
  private String message;

  @Builder
  public Tag(Long repositoryId, String tagName, String ref, String message) {
    this.projectId = repositoryId;
    this.tagName = tagName;
    this.ref = StringUtils.defaultIfBlank(ref, "master");
    this.message = message;
  }
}
