package com.crochet.spring.jpa.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class MappingUtils {
  public static Set<String> convertMultipartFileToString(MultipartFile[] files) {
    if (ObjectUtils.isEmpty(files)) {
      return null;
    }

    return Stream.of(files)
        .map(MappingUtils::encodeFileToBase64)
        .filter(Objects::nonNull)  // Filter out null values
        .collect(Collectors.toSet());
  }

  private static String encodeFileToBase64(MultipartFile file) {
    try {
      return Base64.getEncoder().encodeToString(file.getBytes());
    } catch (IOException e) {
      log.error(e.getMessage());
      return null;
    }
  }
}
