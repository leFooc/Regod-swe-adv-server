package com.regod.app.service;

import com.regod.app.dto.request.BillCreationRequest;
import com.regod.app.entity.Bill;
import com.regod.app.repositories.BillRepository;
import com.regod.app.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public Bill createBill(BillCreationRequest request){
        Bill bill = new Bill();
        bill.setBillName(request.getBillName());
        bill.setCreateDate(request.getCreateDate());
        bill.setDueDate(request.getDueDate());
        bill.setDepartmentName(request.getDepartmentName());
        return billRepository.save(bill);
    }

    public List<Bill> getALlBills(){
        return billRepository.findAll();
    }


    public Optional<Bill> getBillById(String id){
        Optional<Bill> bill = billRepository.findById(id);
        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        return billRepository.findById(id);
    }

    public Bill updateBillById(String id) {
        //to be imple
    }

    public void deleteBillById(String id) {
        Optional<Bill> bill = billRepository.findById(id);
        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        billRepository.deleteById(id);
    }

}
