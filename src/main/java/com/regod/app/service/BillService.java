package com.regod.app.service;

import com.regod.app.dto.request.BillCreationRequest;
import com.regod.app.dto.request.BillModifyRequest;
import com.regod.app.dto.request.InvoiceCreationRequest;
import com.regod.app.dto.request.InvoiceModifyRequest;
import com.regod.app.dto.response.BillDetailResponse;
import com.regod.app.entity.Bill;
import com.regod.app.entity.Invoice;
import com.regod.app.entity.Product;
import com.regod.app.entity.ProductOrderID;
import com.regod.app.repositories.BillRepository;
import com.regod.app.repositories.InvoiceRepository;
import com.regod.app.repositories.ProductOrderRepository;
import com.regod.app.utils.exceptions.NotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillService {
    private Logger logger = LoggerFactory.getLogger("debug");

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Bill createBill(BillCreationRequest request){
        /*Bill bill = new Bill();
        bill.setBillName(request.getBillName());
        bill.setCreateDate(request.getCreateDate());
        bill.setDueDate(request.getDueDate());
        bill.setDepartmentName(request.getDepartmentName());
        return billRepository.save(bill);
        */
        Bill billFound = new Bill();
        String billID = UUID.randomUUID().toString();
        billFound.setId(billID);
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

        logger.debug(request.getProducts().toString());

        productOrderRepository.deleteAllByPOrderID_BillID(billID);
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

        billDetailResponse.setProducts(productOrderRepository.findAllByPOrderID_BillID(billFound.getId()));

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
        Optional<Bill> bill = billRepository.findById(id);
        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        Bill billFound = bill.get();
        if (request.getBillName() != null) {
            billFound.setBillName(request.getBillName());
        }
        if (request.getCreateDate() != null) {
            billFound.setCreateDate(request.getCreateDate());
        }
        if (request.getDueDate() != null) {
            billFound.setDueDate(request.getDueDate());
        }
        if (request.getDepartmentName() != null) {
            billFound.setDepartmentName(request.getDepartmentName());
        }
        if (request.getSupplierBillID() != null) {
            billFound.setSupplierBillID(request.getSupplierBillID());
        }
        if (request.getImgURl() != null) {
            billFound.setImgURl(request.getImgURl());
        }
        if (request.getStatus() != null) {
            billFound.setStatus(request.getStatus());
        }
        if(request.getTotalCost() > 0){
            billFound.setTotalCost(request.getTotalCost());
        }
        if(request.getPayLeft() > 0){
            billFound.setCost(request.getPayLeft());
        }
        if(request.getDeposited() > 0){
            billFound.setDeposited(request.getDeposited());
        }

        productOrderRepository.deleteAllByPOrderID_BillID(id);
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


//    public void addInvoice(String billID, InvoiceCreationRequest request){
//        Optional<Bill> bill = billRepository.findById(billID);
//        if (bill.isEmpty()) {
//            throw new NotFoundException("Bill not found");
//        }
//        Bill billFound = bill.get();
//        Invoice newInvoice = new Invoice();
//        newInvoice.setBillID(billID);
//        newInvoice.setPaidAmount(request.getPaidAmount());
//        newInvoice.setPaidDate(request.getPaidDate());
//        newInvoice.setImgURL(request.getImgURL());
//        invoiceRepository.save(newInvoice);
//    }
//
//    public void modifyInvoice(String billID, InvoiceModifyRequest request){
//        Optional<Bill> bill = billRepository.findById(billID);
//        if (bill.isEmpty()) {
//            throw new NotFoundException("Bill not found");
//        }
//        Optional<Invoice> invoice = invoiceRepository.findById(bill.get().getId());
//        if (invoice.isEmpty()) {
//            throw new NotFoundException("Invoice not found");
//        }
//        Invoice newInvoice = invoice.get();
//        newInvoice.setBillID(billID);
//        newInvoice.setPaidAmount(request.getPaidAmount());
//        newInvoice.setPaidDate(request.getPaidDate());
//        newInvoice.setImgURL(request.getImgURL());
//        invoiceRepository.save(newInvoice);
//    }
//
//    public void deleteInvoice(String billID){
//        Optional<Invoice> invoice = invoiceRepository.findById(billID);
//        if (invoice.isEmpty()) {
//            throw new NotFoundException("Invoice not found");
//        }
//        invoiceRepository.deleteById(billID);
//    }



    public void deleteBillById(String id) {
        Optional<Bill> bill = billRepository.findById(id);
        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        billRepository.deleteById(id);
        productOrderRepository.deleteAllByPOrderID_BillID(id);
        invoiceRepository.deleteById(id);
    }



}
