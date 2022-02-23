package com.example.gitlab_backup.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    ClientHttpRequestFactory configConnection = this.configConnection(httpClient);
    RestTemplate restTemplate = new RestTemplate();

    if (log.isDebugEnabled()) {
      restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(configConnection));
      this.setLogInterceptor(restTemplate);
    } else {
      restTemplate.setRequestFactory(configConnection);
    }

    return restTemplate;
  }

  private ClientHttpRequestFactory configConnection(HttpClient httpClient) {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setReadTimeout(5000);        //읽기시간초과, ms
    factory.setConnectTimeout(5000);     //연결시간초과, ms
    factory.setHttpClient(httpClient);
    return factory;
  }

  private void setLogInterceptor(RestTemplate restTemplate) {
    List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
    if (CollectionUtils.isEmpty(interceptors)) {
      interceptors = new ArrayList<>();
    }
    interceptors.add(new HttpLoggingInterceptor());
    restTemplate.setInterceptors(interceptors);
  }

}
