package com.regod.app.dto.request;

import lombok.Data;

@Data
public class ModifyInvoiceDto {
    private String paidDate;
    private String paidAmount;
    private String imgURL;
}
