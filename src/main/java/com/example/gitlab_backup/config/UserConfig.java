package com.example.gitlab_backup.config;

import com.example.gitlab_backup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

  @Autowired
  GitLabConfig gitLabConfig;

  @Bean
  public User user() {
    return User.builder()
      .apiToken(gitLabConfig.apiToken())
      .name("hyo-jip")
      .build();
  }
}
