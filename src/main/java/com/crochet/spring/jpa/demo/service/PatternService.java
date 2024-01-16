package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.PatternCreationDTO;
import com.crochet.spring.jpa.demo.dto.PatternDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PatternService {
    String create(PatternCreationDTO request, MultipartFile[] files);

    byte[] getFirsFileByPattern(UUID patternId);

    PatternDTO getPattern(UUID customerId, UUID patternId);
}
