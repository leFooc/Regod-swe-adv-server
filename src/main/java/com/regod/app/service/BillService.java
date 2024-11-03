package com.regod.app.service;

import com.regod.app.dto.request.BillCreationRequest;
import com.regod.app.dto.request.BillModifyRequest;
import com.regod.app.dto.response.BillDetailResponse;
import com.regod.app.entity.Bill;
import com.regod.app.entity.Product;
import com.regod.app.entity.ProductOrderID;
import com.regod.app.repositories.BillRepository;
import com.regod.app.repositories.ProductOrderRepository;
import com.regod.app.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public Bill createBill(BillCreationRequest request){
        /*Bill bill = new Bill();
        bill.setBillName(request.getBillName());
        bill.setCreateDate(request.getCreateDate());
        bill.setDueDate(request.getDueDate());
        bill.setDepartmentName(request.getDepartmentName());
        return billRepository.save(bill);
        */
        Bill billFound = new Bill();
        String billID = billFound.getId();
        billFound.setBillName(request.getBillName());
        billFound.setCreateDate(request.getCreateDate());
        billFound.setDueDate(request.getDueDate());
        billFound.setDepartmentName(request.getDepartmentName());
        billFound.setDueDate(request.getDueDate());
        billFound.setSupplierBillID(request.getSupplierBillID());
        billFound.setImgURl(request.getImgURl());

        billFound.setTotalCost(request.getTotalCost());
        billFound.setCost(request.getPayLeft());
        billFound.setDeposited(request.getDeposited());

        billFound.setStatus(request.getStatus());

        productOrderRepository.deleteByIdBillID(billID);
        List<Product> newProducts = new ArrayList<>();
        for (int i = 1; i <= request.getProducts().size(); i++) {
            Product newProduct = new Product();
            newProduct.setName(request.getProducts().get(i-1).getName());
            newProduct.setPrice(request.getProducts().get(i-1).getPrice());
            newProduct.setQuantity(request.getProducts().get(i-1).getQuantity());
            newProduct.setPOrderID(new ProductOrderID(billID, "" + i));
            newProducts.add(newProduct);
        }
        productOrderRepository.saveAll(newProducts);
        return billRepository.save(billFound);
    }



    public List<Bill> getALlBills(){
        return billRepository.findAll();
    }

    public BillDetailResponse getBillDetail(String billID){
        Optional<Bill> bill = billRepository.findById(billID);

        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        Bill billFound = bill.get();
        BillDetailResponse billDetailResponse = new BillDetailResponse();
        billDetailResponse.setId(billFound.getId());
        billDetailResponse.setBillName(billFound.getBillName());
        billDetailResponse.setCreateDate(billFound.getCreateDate());
        billDetailResponse.setDueDate(billFound.getDueDate());
        billDetailResponse.setDepartmentName(billFound.getDepartmentName());
        //for invoice status check
        billDetailResponse.setStatus("UNPAID");
        //end for invoice
        billDetailResponse.setDeposited(billFound.getDeposited());
        billDetailResponse.setPayLeft(billFound.getCost());
        billDetailResponse.setTotalCost(billFound.getTotalCost());

        billDetailResponse.setSupplierBillID(billFound.getSupplierBillID());
        billDetailResponse.setImgURl(billFound.getImgURl());

        billDetailResponse.setProducts(productOrderRepository.findByIdBillID(billFound.getId()));

        return billDetailResponse;

    }


    public Bill getBillById(String id){
        Optional<Bill> bill = billRepository.findById(id);

        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        return bill.get();
    }





    public void updateBillById(String id, BillModifyRequest request) {
        //to be imple
        Optional<Bill> bill = billRepository.findById(id);
        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        Bill billFound = bill.get();
        billFound.setBillName(request.getBillName());
        billFound.setCreateDate(request.getCreateDate());
        billFound.setDueDate(request.getDueDate());
        billFound.setDepartmentName(request.getDepartmentName());
        billFound.setDueDate(request.getDueDate());
        billFound.setSupplierBillID(request.getSupplierBillID());
        billFound.setImgURl(request.getImgURl());

        billFound.setTotalCost(request.getTotalCost());
        billFound.setCost(request.getPayLeft());
        billFound.setDeposited(request.getDeposited());

        billFound.setStatus(request.getStatus());

        productOrderRepository.deleteByIdBillID(id);
        List<Product> newProducts = new ArrayList<>();
        for (int i = 1; i <= request.getProducts().size(); i++) {
            Product newProduct = new Product();
            newProduct.setName(request.getProducts().get(i-1).getName());
            newProduct.setPrice(request.getProducts().get(i-1).getPrice());
            newProduct.setQuantity(request.getProducts().get(i-1).getQuantity());
            newProduct.setPOrderID(new ProductOrderID(id, "" + i));
            newProducts.add(newProduct);
        }
        productOrderRepository.saveAll(newProducts);
        billRepository.save(billFound);

    }

    public void deleteBillById(String id) {
        Optional<Bill> bill = billRepository.findById(id);
        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        billRepository.deleteById(id);
    }

}
