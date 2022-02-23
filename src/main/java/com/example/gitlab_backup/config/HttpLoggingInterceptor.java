package com.example.gitlab_backup.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    log.debug("Request header: {}", request.getHeaders());
    log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));

    ClientHttpResponse response = execution.execute(request, body);

//    try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
//      String responseBody = reader
//        .lines()
//        .collect(Collectors.joining("\n"));
//      log.debug("Response body: {}", responseBody);
//    }

    return response;
  }
}
