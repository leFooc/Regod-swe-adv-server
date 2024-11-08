package com.regod.app.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateInvoiceDto {
    @NotEmpty(message = "Paid date is required")
    private String paidDate;
    @NotEmpty(message = "Paid amount is required")
    private String paidAmount;

    private String imgURL;
};
