package com.regod.app.entity;

import jakarta.persistence.Entity;
import lombok.Data;



@Data

public class Product{
    private String name;
    private int price;
    private int quantity;
    private int total;
}
