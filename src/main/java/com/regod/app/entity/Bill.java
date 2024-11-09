package com.regod.app.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter @Setter
public class Bill {
    @jakarta.persistence.Id
    @Id
    private String id;
    private String billName;
    private String departmentName;
    private String createDate;
    private String dueDate;
    private String status;
    private long cost;

    private String supplierBillID;
    private String imgURl;
    private int totalCost;
    private float deposited;




    //private ArrayList<Product> productsList;
}
