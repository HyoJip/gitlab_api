package com.example.gitlab_backup.backup.dto.request;

import com.example.gitlab_backup.model.Tag;
import com.google.common.base.Preconditions;

public class TagCreateRequest implements GitRequest {

  private final Long id;
  private final String tagName;
  private final String ref;
  private final String message;

  public TagCreateRequest(Tag tag) {
    Preconditions.checkNotNull(tag, "tag must be provided.");
    this.id = tag.getProjectId();
    this.tagName = tag.getTagName();
    this.ref = tag.getRef();
    this.message = tag.getMessage();
  }

  public TagCreateRequest of(Tag tag) {
    return new TagCreateRequest(tag);
  }

  @Override
  public String getUrl() {
    return "https://gitlab.com/api/v4/projects/" + id + "/repository/tags";
  }
}
