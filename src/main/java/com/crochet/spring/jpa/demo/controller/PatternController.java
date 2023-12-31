package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.PatternRequest;
import com.crochet.spring.jpa.demo.payload.response.PatternResponse;
import com.crochet.spring.jpa.demo.service.contact.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/patterns")
public class PatternController {
    @Autowired
    private PatternService patternService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestPart PatternRequest request,
                                         @RequestPart MultipartFile[] files) {
        var response = patternService.create(request, files);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<PatternResponse> getPattern(@RequestParam("customerId") String customerId,
                                                      @RequestParam("patternId") String patternId) {
        var response = patternService.getPattern(customerId, patternId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getFile")
    public ResponseEntity<byte[]> getFile(@RequestParam("patternId") String id) {
        var url = patternService.getFirsFileByPattern(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(url);
    }
}
