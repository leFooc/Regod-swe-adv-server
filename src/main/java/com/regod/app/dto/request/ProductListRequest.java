package com.regod.app.dto.request;

import lombok.Data;

@Data
public class ProductListRequest {
    private String name;
    private int price;
    private int quantity;
    private int total;
}
