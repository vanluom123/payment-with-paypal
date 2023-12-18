package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.mapper.FileMapper;
import com.crochet.spring.jpa.demo.model.FileModal;
import com.crochet.spring.jpa.demo.repository.FileRepository;
import com.crochet.spring.jpa.demo.repository.ProductRepository;
import com.crochet.spring.jpa.demo.payload.response.FileResponse;
import com.crochet.spring.jpa.demo.service.contact.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public FileResponse uploadFile(String productId, MultipartFile file) {
        var product = productRepository.findById(UUID.fromString(productId))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        FileModal fileModal = mapToFileModal(file);
        fileModal.setProduct(product);
        fileModal = fileRepository.save(fileModal);
        return FileMapper.INSTANCE.fileModalToFileResult(fileModal);
    }

    private FileModal mapToFileModal(MultipartFile file) {
        var fileModal = FileModal.builder()
                .name(file.getName())
                .originalFileName(file.getOriginalFilename())
                .contentType(file.getContentType());
        try {
            fileModal.bytes(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileModal.build();
    }

    @Transactional
    @Override
    public List<FileResponse> uploadFiles(String productId, MultipartFile[] files) {
        var product = productRepository.findById(UUID.fromString(productId))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<FileModal> fileModals = Stream.of(files)
                .map(file -> {
                    var fileModal = mapToFileModal(file);
                    fileModal.setProduct(product);
                    return fileModal;
                }).collect(Collectors.toList());

        var fileModalResult = fileRepository.saveAll(fileModals);
        return FileMapper.INSTANCE.fileModalToFileResults(fileModalResult);
    }

    @Override
    public FileResponse getByName(String fileName) {
        var fileModal = fileRepository.findByOriginalFileName(fileName)
                .orElseThrow(() -> new RuntimeException("File not found"));

        return FileMapper.INSTANCE.fileModalToFileResult(fileModal);
    }

    @Override
    public byte[] getByteByName(String fileName) {
        var fileModal = fileRepository.findByOriginalFileName(fileName)
                .orElseThrow(() -> new RuntimeException("File not found"));

        return Base64.getDecoder().decode(fileModal.getBytes());
    }
}
