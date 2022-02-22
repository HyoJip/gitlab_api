package com.example.gitlab_backup.backup.dto;

import java.util.List;
import java.util.Map;

public record Asset (
  List<Map<String, Object>> sources
){ }
