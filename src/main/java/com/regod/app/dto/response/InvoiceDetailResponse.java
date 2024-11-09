package com.regod.app.dto.response;

import com.regod.app.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class InvoiceDetailResponse {
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

    private List<Product> products;
}
