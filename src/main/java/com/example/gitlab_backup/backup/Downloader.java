package com.example.gitlab_backup.backup;

import com.example.gitlab_backup.config.GitLabConfig;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class Downloader {

  private final GitLabConfig config;
  private final RestTemplate restTemplate;

  public void download(String url, String filename) {
    File tempFile = restTemplate.execute(url, HttpMethod.GET, null, this::storeTempStream);
    copyTo(tempFile, Path.of(config.location(), filename));
  }

  private File storeTempStream(ClientHttpResponse clientResponse) throws IOException {
    File file = File.createTempFile("gitlab", "tmp");
    try(FileOutputStream fos = new FileOutputStream(file)) {
      StreamUtils.copy(clientResponse.getBody(), fos);
    }
    return file;
  }

  private void copyTo(File tempFile, Path filepath) {
    try {
      Files.createDirectories(filepath.getParent());
      FileCopyUtils.copy(tempFile, filepath.toFile());
    } catch (IOException e) {
      throw new RuntimeException("fail downloading the File", e);
    } finally {
      try {
        Files.deleteIfExists(tempFile.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
