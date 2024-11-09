package com.regod.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InvoiceResponse {
    private String billID;
    private String paidDate;
    private String imgURL;

    private String billName;
    private String departmentName;
    private String createDate;
    private String dueDate;
    private String status;

    private String supplierBillID;

    private int totalCost;
    private float deposited;

}
