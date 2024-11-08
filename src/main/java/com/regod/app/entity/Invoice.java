package com.regod.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Entity
@Data
public class Invoice {
    @jakarta.persistence.Id
    @Id
    private String billID;
    private String paidDate;
    private String paidAmount;
    private String imgURL;
}
