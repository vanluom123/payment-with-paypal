package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.mapper.PatternMapper;
import com.crochet.spring.jpa.demo.model.Pattern;
import com.crochet.spring.jpa.demo.payload.request.PatternRequest;
import com.crochet.spring.jpa.demo.payload.response.PatternResponse;
import com.crochet.spring.jpa.demo.repository.OrderPatternDetailRepo;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.service.contact.PatternService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public String create(PatternRequest request, MultipartFile[] files) {
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
        List<String> urls = new ArrayList<>();
        for (var file : files) {
            urls.add(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        return urls;
    }

    @Override
    public byte[] getFirsFileByPattern(String patternId) {
        var pattern = getById(patternId);
        var fileUrl = pattern.getFiles().getFirst();
        return Base64.getDecoder().decode(fileUrl.getBytes());
    }

    private Pattern getById(String patternId) {
        var pattern = patternRepo.findById(UUID.fromString(patternId))
                .orElseThrow(() -> new RuntimeException("Pattern not found"));
        return pattern;
    }

    @Override
    public PatternResponse getPattern(String customerId, String patternId) {
        var pattern = patternRepo.findCompletedPatterns(UUID.fromString(customerId),
                        UUID.fromString(patternId))
                .orElseThrow(() -> new RuntimeException("Pattern not ordered"));
        return PatternMapper.INSTANCE.toResponse(pattern);
    }
}
