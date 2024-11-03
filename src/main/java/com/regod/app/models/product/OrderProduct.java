package com.regod.app.models.product;

import com.regod.app.models.bills.Bill;
import lombok.Data;

@Data
public class OrderProduct {
    private Bill bill;
    private Products product;
    private int quantity;
}
