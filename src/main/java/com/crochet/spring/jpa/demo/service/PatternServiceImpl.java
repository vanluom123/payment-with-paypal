package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Pattern;
import com.crochet.spring.jpa.demo.payload.request.PatternRequest;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.service.contact.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class PatternServiceImpl implements PatternService {
    @Autowired
    private PatternRepo patternRepo;

    @Override
    public String create(PatternRequest request, MultipartFile[] files) {
        var patternBuilder = Pattern.builder()
                .name(request.getName())
                .price(request.getPrice())
                .currencyCode(request.getCurrencyCode());

        var fileUrls = Stream.of(files)
                .map(file -> {
                    try {
                        return Base64.getEncoder().encodeToString(file.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
        patternBuilder.files(fileUrls);

        patternRepo.save(patternBuilder.build());
        return "Create success";
    }

    @Override
    public byte[] getFirsFileByPattern(String patternId) {
        var pattern = patternRepo.findById(UUID.fromString(patternId))
                .orElseThrow(() -> new RuntimeException("Pattern not found"));
        var fileUrl = pattern.getFiles().getFirst();
        return Base64.getDecoder().decode(fileUrl.getBytes());
    }
}
