package com.alpersayin.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiErrorResponse {
    private String message;
    private String error;
    private LocalDateTime timestamp;
}
