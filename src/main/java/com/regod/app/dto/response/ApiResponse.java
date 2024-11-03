package com.regod.app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @Getter @Setter
    private int code = 0;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private T data;
}
