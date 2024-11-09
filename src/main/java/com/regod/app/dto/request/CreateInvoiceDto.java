package com.regod.app.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateInvoiceDto {
    @NotEmpty(message = "Paid date is required")
    private String paidDate;

    private String imgURL;
};
