package com.regod.app.dto.response;

import lombok.Data;

@Data
public class InvoiceResponse {
    private String billID;
    private String paidDate;
    private String paidAmount;
    private String imgURL;
}
