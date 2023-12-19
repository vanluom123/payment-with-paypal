package com.crochet.spring.jpa.demo.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponse {
    private String name;

    private String originalFileName;

    private String contentType;

    private byte[] bytes;
}
