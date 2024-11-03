package com.regod.app.models.bills;

import com.regod.app.models.product.OrderProduct;
import lombok.Data;

import java.util.List;
@Data
public class BillDetail {
    private int id;
    private String supplierBillID;

    private String billName;
    //manual
    //@Getter @Setter
    //private
    //@Getter @Setter
    private String ownerName;
    //@Getter @Setter
    private String ssn;
    //@Getter @Setter
    private String departmentName;
    //@Getter @Setter
    private String deadlineDate;
    //@Getter @Setter
    private String imgURl;

    private List<OrderProduct> orderProducts;

    private String invoiceID;

    private float totalPrice;
    private float deposited;
    private String paidCurrency;
}
