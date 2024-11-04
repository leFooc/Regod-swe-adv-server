package com.regod.app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class ApiResponse<T> {
    private Integer code = 200;

    private String message;

    private T data;

    void setCode(ResponseCode code) {
        this.code = code.get();
    }
}
