package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.result.FileResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileResult uploadFile(String productId, MultipartFile imageFile);

    List<FileResult> uploadFiles(String productId, MultipartFile[] files);

    FileResult getByName(String fileName);

    byte[] getByteByName(String fileName);
}
