package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.request.PatternRequest;
import com.crochet.spring.jpa.demo.payload.response.PatternResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PatternService {
    String create(PatternRequest request, MultipartFile[] files);

    byte[] getFirsFileByPattern(UUID patternId);

    PatternResponse getPattern(UUID customerId, UUID patternId);
}
