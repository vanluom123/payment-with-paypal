package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.PatternRequest;
import com.crochet.spring.jpa.demo.payload.response.PatternResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PatternService {
    String create(PatternRequest request, MultipartFile[] files);

    byte[] getFirsFileByPattern(String patternId);

    PatternResponse getPattern(String customerId, String patternId);
}
