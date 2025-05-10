package com.techzen.academy_n0325c1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T>{
    Integer code;
    String message;
    T data;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
