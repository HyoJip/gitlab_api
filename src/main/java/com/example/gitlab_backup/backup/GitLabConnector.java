package com.example.gitlab_backup.backup;

import com.example.gitlab_backup.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GitLabConnector {

  private final User user;
  private final RestTemplate restTemplate;

  public <T> ResponseEntity<T> get(String url, Object body, Class responseType) {
    return restTemplate.exchange(url, HttpMethod.GET, createRequest(body), responseType);
  }

  public <T> ResponseEntity<T> get(String url, Object body, ParameterizedTypeReference<T> responseType) {
    return restTemplate.exchange(url, HttpMethod.GET, createRequest(body), responseType);
  }
  public <T> ResponseEntity<T> post(String url, Object body, Class responseType) {
    return restTemplate.exchange(url, HttpMethod.POST, createRequest(body), responseType);
  }

  public <T> ResponseEntity<T> post(String url, Object body, ParameterizedTypeReference<T> responseType) {
    return restTemplate.exchange(url, HttpMethod.POST, createRequest(body), responseType);
  }

  private <T> HttpEntity<T> createRequest(T body) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("PRIVATE-TOKEN", user.getApiToken());
    return new HttpEntity<>(body, headers);
  }

}
