package com.example.gitlab_backup.backup.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BackupRequest(
  @JsonProperty String title,
  @JsonProperty String description
) {

}
