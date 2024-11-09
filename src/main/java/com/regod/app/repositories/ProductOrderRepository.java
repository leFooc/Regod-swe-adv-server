package com.regod.app.repositories;

import com.regod.app.entity.Product;
import com.regod.app.entity.ProductOrderID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<Product, String> {
    List<Product> findAllByBillId(String billID);

    void deleteAllByBillId(String billID);
}
