package com.regod.app.entity;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Embeddable
public class ProductOrderID implements Serializable {
    private String billID;

    private String productID; //1,2,3

    // default constructor
    public ProductOrderID() {}
    public ProductOrderID(String bill, String productID) {
        this.billID = bill;
        this.productID = productID;
    }

    // equals() and hashCode()
}