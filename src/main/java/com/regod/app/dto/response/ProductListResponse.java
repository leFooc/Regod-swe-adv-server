package com.regod.app.dto.response;

import lombok.Data;

@Data
public class ProductListResponse {
    private String name;
    private int price;
    private int quantity;
    private int total;
}
