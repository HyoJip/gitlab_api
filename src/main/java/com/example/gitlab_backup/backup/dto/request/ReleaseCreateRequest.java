package com.example.gitlab_backup.backup.dto.request;

import com.example.gitlab_backup.model.Release;
import com.example.gitlab_backup.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.base.Preconditions;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReleaseCreateRequest implements GitRequest {

  @JsonIgnore
  private final Long projectId;
  private String name;
  private final String tagName;
  private final String ref;
  private String description;

  public ReleaseCreateRequest(Release release) {
    Preconditions.checkNotNull(release, "release must be provided.");
    Tag tag = release.getTag();
    this.projectId = tag.getProjectId();
    this.name = release.getName();
    this.tagName = tag.getTagName();
    this.ref = tag.getRef();
    this.description = release.getDescription();
  }

  @Override
  public String getUrl() {
    return "https://gitlab.com/api/v4/projects/" + projectId + "/releases";
  }
}










