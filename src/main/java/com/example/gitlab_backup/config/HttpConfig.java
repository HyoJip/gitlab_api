package com.example.gitlab_backup.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpConfig {

  @Bean
  HttpClient httpClient() {
    return HttpClients.custom()
      .setMaxConnTotal(5)    //최대 오픈되는 커넥션 수
      .setMaxConnPerRoute(5)   //IP, 포트 1쌍에 대해 수행할 커넥션 수
      .build();
  }

  @Bean
  public RestTemplate factory(HttpClient httpClient) {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setReadTimeout(5000);        //읽기시간초과, ms
    factory.setConnectTimeout(5000);     //연결시간초과, ms
    factory.setHttpClient(httpClient);

    return new RestTemplate(factory);
  }

}
