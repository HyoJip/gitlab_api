package com.example.gitlab_backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GitlabBackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitlabBackupApplication.class, args);
	}

}
