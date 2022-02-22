package com.example.gitlab_backup.backup.dto.response;

import com.example.gitlab_backup.backup.dto.Asset;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReleaseCreateResponse {
  private String tagName;
  private String description;
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime releasedAt;
  private Asset assets;
}
