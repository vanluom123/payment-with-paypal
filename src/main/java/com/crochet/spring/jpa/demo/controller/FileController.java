package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.response.FileResponse;
import com.crochet.spring.jpa.demo.service.contact.FileService;
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

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("productId") String productId,
                                                   @RequestPart MultipartFile file) {
        var result = fileService.uploadFile(productId, file);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/uploads")
    public ResponseEntity<List<FileResponse>> uploadFiles(@RequestParam("productId") String productId,
                                                          @RequestPart MultipartFile[] files) {
        var results = fileService.uploadFiles(productId, files);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/getFile")
    public ResponseEntity<byte[]> getByName(@RequestParam("fileName") String fileName) {
        byte[] bytes = fileService.getByteByName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}
