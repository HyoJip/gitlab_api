package com.example.gitlab_backup.config;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "gitlab")
public record GitLabConfig(String apiToken, String location) {

  public GitLabConfig(String apiToken, String location) {
    Preconditions.checkArgument(StringUtils.isNotBlank(apiToken), "apiToken must be provided.");

    this.apiToken = apiToken;
    this.location = StringUtils.defaultIfBlank(location, "/appdata/repository/backup");
  }

}
