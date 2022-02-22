package com.example.gitlab_backup.backup.dto.request;

public class ProjectFindRequest implements GitRequest {

  private static final String URL = "https://gitlab.com/api/v4/projects?membership=true";

  @Override
  public String getUrl() {
    return URL;
  }
}
