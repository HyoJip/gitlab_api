package com.example.gitlab_backup.backup;

import com.example.gitlab_backup.backup.dto.request.ProjectFindRequest;
import com.example.gitlab_backup.backup.dto.request.ReleaseCreateRequest;
import com.example.gitlab_backup.backup.dto.response.ReleaseCreateResponse;
import com.example.gitlab_backup.model.Project;
import com.example.gitlab_backup.model.Release;
import com.example.gitlab_backup.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BackupService {

  private final GitLabConnector connector;
  private final Downloader downloader;

  public List<Project> findAllProject() {
    ProjectFindRequest request = new ProjectFindRequest();
    ResponseEntity<List<Project>> response = connector.get(request.getUrl(),null, new ParameterizedTypeReference<>() {});
    return response.getBody();
  }

  public void release(String title, String description) {
    // 1. 멤버로 참여한 프로젝트 ID 조회
    List<Project> allProject = this.findAllProject();
    allProject.forEach(project -> {
      // 2. 태그 & 릴리즈 생성
      Tag tag = project.tag(title, "master", description);
      Release release = project.release(tag, title, description);
      ReleaseCreateResponse response = this.createRelease(release);
      // 3. 다운로드
      Map<String, Object> files = response.getAssets().sources().get(0);
      downloader.download(files.get("url").toString(), project.getName() + UUID.randomUUID().toString().replaceAll("-", "") + ".zip");
    });
  }

  public ReleaseCreateResponse createRelease(Release release) {
    ReleaseCreateRequest request = new ReleaseCreateRequest(release);
    ResponseEntity<ReleaseCreateResponse> response = connector.post(request.getUrl(), request, ReleaseCreateResponse.class);
    return response.getBody();
  }
}
