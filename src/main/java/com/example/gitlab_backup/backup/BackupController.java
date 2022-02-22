package com.example.gitlab_backup.backup;

import com.example.gitlab_backup.backup.dto.request.BackupRequest;
import com.example.gitlab_backup.config.GitLabConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BackupController {

  private final BackupService backupService;

  @PostMapping(value = "/api/v1/repository/backup")
  public void backupRepository(@RequestBody BackupRequest request) {
    backupService.release(request.title(), request.description());
  }
}
