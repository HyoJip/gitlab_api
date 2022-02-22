package com.example.gitlab_backup.backup;

import com.example.gitlab_backup.backup.dto.Asset;
import com.example.gitlab_backup.backup.dto.request.ReleaseCreateRequest;
import com.example.gitlab_backup.backup.dto.response.ReleaseCreateResponse;
import com.example.gitlab_backup.model.Project;
import com.example.gitlab_backup.model.Release;
import com.example.gitlab_backup.model.Tag;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BackupServiceTest {

  public static final String GITLAB_APITOKEN = "GITLAB_APITOKEN";
  @InjectMocks BackupService service;
  @Mock GitLabConnector gitLabConnector;
//  @Mock GitLabConfig config;

  @Test
  @DisplayName("[프로젝트 조회] 멤버 역할이 있는 회원의 프로젝트만 조회한다.")
  void findAllProject_whenMemberRoleIsProvided_receiveProjectModel() {
    Project project = createTestProject();
    ResponseEntity<List<Project>> response = ResponseEntity.of(Optional.of(List.of(project)));
    given(gitLabConnector.get(anyString(), any(), any(ParameterizedTypeReference.class))).willReturn(response);

    List<Project> projects = service.findAllProject();

    assertThat(projects).size().isEqualTo(1);
  }

  @Test
  @DisplayName("[릴리즈 생성] 유효한 릴리즈 생성 요청은, 생성된 릴리즈를 리턴한다.")
  void createRelease_whenReleaseIsValid_receiveCreatedRelease() {
    Project project = createTestProject();
    Tag tag = project.tag("test-tag","test-message");
    Release release = project.release(tag,"test-release","test-description");
    ReleaseCreateResponse response = new ReleaseCreateResponse(tag.getTagName(), release.getDescription(), release.getName(), LocalDateTime.now(), LocalDateTime.now(), new Asset(Collections.emptyList()));
    given(gitLabConnector.post(anyString(), any(ReleaseCreateRequest.class), any(Class.class)))
      .willReturn(ResponseEntity.of(Optional.of(response)));

    ReleaseCreateResponse result = service.createRelease(release);

    assertThat(result.getTagName()).isEqualTo(tag.getTagName());
    assertThat(result.getName()).isEqualTo(release.getName());
  }

  private Project createTestProject() {
    Project project = Project.builder()
      .id(1L)
      .name("test-project")
      .defaultBranch("master")
      .build();
    return project;
  }
}