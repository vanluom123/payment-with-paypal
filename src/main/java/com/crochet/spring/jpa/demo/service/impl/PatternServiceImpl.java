package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.PatternCreationDTO;
import com.crochet.spring.jpa.demo.dto.PatternDTO;
import com.crochet.spring.jpa.demo.mapper.PatternMapper;
import com.crochet.spring.jpa.demo.model.Pattern;
import com.crochet.spring.jpa.demo.repository.OrderPatternDetailRepo;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.service.PatternService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PatternServiceImpl implements PatternService {
    @Autowired
    private PatternRepo patternRepo;
    @Autowired
    private OrderPatternDetailRepo orderPatternDetailRepo;
    @Autowired
    private PatternMapper patternMapper;

    @Override
    public String create(PatternCreationDTO request, MultipartFile[] files) {
        var pattern = Pattern.builder()
                .name(request.getName())
                .price(request.getPrice())
                .currencyCode(request.getCurrencyCode())
                .files(convertMultipartToString(files))
                .build();
        patternRepo.save(pattern);
        return "Create success";
    }

    @SneakyThrows
    private List<String> convertMultipartToString(MultipartFile[] files) {
        if (ObjectUtils.isEmpty(files)) {
            return null;
        }
        List<String> urls = new ArrayList<>();
        for (var file : files) {
            urls.add(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        return urls;
    }

    @Override
    public byte[] getFirsFileByPattern(UUID patternId) {
        var pattern = getById(patternId);
        var fileUrl = pattern.getFiles().getFirst();
        return Base64.getDecoder().decode(fileUrl.getBytes());
    }

    private Pattern getById(UUID patternId) {
        var pattern = patternRepo.findById(patternId)
                .orElseThrow(() -> new RuntimeException("Pattern not found"));
        return pattern;
    }

    @Override
    public PatternDTO getPattern(UUID customerId, UUID patternId) {
        var pattern = patternRepo.findCompletedPatterns(customerId, patternId)
                .orElseThrow(() -> new RuntimeException("Pattern not ordered"));
        return patternMapper.toResponse(pattern);
    }
}
