package com.regod.app.entity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
public class Product{
    @EmbeddedId
    private ProductOrderID pOrderID;
    private String name;
    private int price;
    private int quantity;
    private int total;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
