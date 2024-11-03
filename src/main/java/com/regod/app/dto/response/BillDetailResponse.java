package com.regod.app.dto.response;

import com.regod.app.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class BillDetailResponse {
    private String id;
    private String billName;
    private String departmentName;
    private String createDate;
    private String dueDate;
    private String status;

    private String supplierBillID;
    private String imgURl;


    private int totalCost;
    private float deposited;
    private long payLeft;

    List<Product> products;


}
