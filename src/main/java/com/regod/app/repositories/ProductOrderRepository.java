package com.regod.app.repositories;

import com.regod.app.entity.Product;
import com.regod.app.entity.ProductOrderID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<Product, ProductOrderID> {
    List<Product> findAllByPOrderID_BillID(String billID);

    void deleteAllByPOrderID_BillID(String billID);
}
