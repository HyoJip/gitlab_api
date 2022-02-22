package com.example.gitlab_backup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@JsonDeserialize(builder = Project.ProjectBuilder.class)
@ToString
public class Project {
  private final Long id;
  private final String name;
  private final String defaultBranch;

  @Builder(builderClassName = "ProjectBuilder")
  public Project(Long id, String name, @JsonProperty("default_branch") String defaultBranch) {
    this.id = id;
    this.name = name;
    this.defaultBranch = defaultBranch;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class ProjectBuilder {
  }

  public Tag tag(String tagName, String message) {
    return this.tag(tagName, "master", message);
  }

  public Tag tag(String tagName, String ref, String message) {
    return Tag.builder()
      .repositoryId(id)
      .tagName(tagName)
      .ref(ref)
      .message(message)
      .build();
  }

  public Release release(Tag tag, String name, String description) {
    return Release.builder()
      .tag(tag)
      .name(name)
      .description(description)
      .build();
  }
}
