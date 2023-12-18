package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileResponse uploadFile(String productId, MultipartFile imageFile);

    List<FileResponse> uploadFiles(String productId, MultipartFile[] files);

    FileResponse getByName(String fileName);

    byte[] getByteByName(String fileName);
}
