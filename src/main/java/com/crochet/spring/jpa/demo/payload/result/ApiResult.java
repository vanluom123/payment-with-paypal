package com.crochet.spring.jpa.demo.payload.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class ApiResult<T> {
    private String message;
    private T result;
}
