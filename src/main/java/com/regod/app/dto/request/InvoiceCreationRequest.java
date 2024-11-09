package com.regod.app.dto.request;

import lombok.Data;

@Data
public class InvoiceCreationRequest {
    private String billID;
    private String paidDate;
    private String paidAmount;
    private String imgURL;
}
