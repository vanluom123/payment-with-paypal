package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.PatternRequest;
import org.springframework.web.multipart.MultipartFile;

public interface PatternService {
    String create(PatternRequest request, MultipartFile[] files);

    byte[] getFirsFileByPattern(String patternId);
}
