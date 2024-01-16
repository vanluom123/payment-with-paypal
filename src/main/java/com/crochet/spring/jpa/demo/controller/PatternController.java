package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.dto.PatternCreationDTO;
import com.crochet.spring.jpa.demo.dto.PatternDTO;
import com.crochet.spring.jpa.demo.service.PatternService;
import com.crochet.spring.jpa.demo.type.paypal.CurrencyCode;
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

import java.util.UUID;

@RestController
@RequestMapping("/api/patterns")
public class PatternController {
  @Autowired
  private PatternService patternService;

  @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<String> create(
      @RequestParam String name,
      @RequestParam double price,
      @RequestParam("currency_code") CurrencyCode currencyCode,
      @RequestPart(required = false) MultipartFile[] files
  ) {
    PatternCreationDTO request = new PatternCreationDTO(name, price, currencyCode);
    var response = patternService.create(request, files);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/getById")
  public ResponseEntity<PatternDTO> getPattern(@RequestParam("customerId") UUID customerId,
                                               @RequestParam("patternId") UUID patternId) {
    var response = patternService.getPattern(customerId, patternId);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/getFile")
  public ResponseEntity<byte[]> getFile(@RequestParam("patternId") UUID id) {
    var url = patternService.getFirsFileByPattern(id);
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.IMAGE_PNG)
        .body(url);
  }
}
