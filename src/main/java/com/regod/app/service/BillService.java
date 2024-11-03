package com.regod.app.service;

import com.regod.app.dto.request.BillCreationRequest;
import com.regod.app.entity.Bill;
import com.regod.app.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Bill> GetALlBills(){
        return billRepository.findAll();
    }
}
