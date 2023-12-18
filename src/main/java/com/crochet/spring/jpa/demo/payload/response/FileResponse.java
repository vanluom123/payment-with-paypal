package com.crochet.spring.jpa.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FileResponse {
    private String name;

    private String originalFileName;

    private String contentType;

    private byte[] bytes;
}
