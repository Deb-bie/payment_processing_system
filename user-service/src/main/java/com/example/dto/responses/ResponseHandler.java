package com.example.dto.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseHandler<T> {
    private Integer code;
    private HttpStatus status;
    private T message;
}
