package com.alpersayin.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;
    private static LocalDateTime timestamp = LocalDateTime.now();
}
