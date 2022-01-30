package com.alpersayin.getir.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> extends Response {
    private T data;
}
