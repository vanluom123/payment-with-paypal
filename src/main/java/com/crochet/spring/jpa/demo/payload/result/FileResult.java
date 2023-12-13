package com.crochet.spring.jpa.demo.payload.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FileResult {
    private String name;

    private String originalFileName;

    private String contentType;

    private byte[] bytes;
}
