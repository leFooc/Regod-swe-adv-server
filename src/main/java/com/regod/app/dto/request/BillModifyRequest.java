package com.regod.app.dto.request;

import com.regod.app.entity.Product;
import lombok.Data;

import java.util.List;
@Data
public class BillModifyRequest {
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
