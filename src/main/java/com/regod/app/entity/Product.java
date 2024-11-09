package com.regod.app.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;

@Data
@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private int price;
    private int quantity;
    private int total;
    private String billId;
}
