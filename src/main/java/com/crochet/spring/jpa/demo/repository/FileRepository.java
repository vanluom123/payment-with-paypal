package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.FileModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileModal, UUID> {
    Optional<FileModal> findByOriginalFileName(String fileName);
}