package com.regod.app.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateOrderDto {
    @NotEmpty
    private String data;
}
