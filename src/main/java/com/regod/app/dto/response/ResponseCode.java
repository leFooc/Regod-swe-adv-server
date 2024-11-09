package com.regod.app.dto.response;

public enum ResponseCode {
    // To be customized

    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private Integer value;

    private ResponseCode(Integer value) {
        this.value = value;
    }

    public Integer get() {
        return this.value;
    }
};
