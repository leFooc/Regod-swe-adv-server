package com.regod.app.models.invoices;

import com.regod.app.models.bills.Bill;

public class SupplierInvoice {
    private int id;
    private int supplierId;
    private Bill bill;
    private String paidDate;
    private String paidCost;
    private String paidCurrency;

    private String imgURL;
}
