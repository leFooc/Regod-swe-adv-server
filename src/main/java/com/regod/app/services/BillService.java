package com.regod.app.services;

import com.regod.app.models.bills.Bill;
import com.regod.app.repositories.BillRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
    public List<Bill> GetALlBills(){
        return billRepository.findAll();
    }
    //public Bill GetBillById(int id){
    //    return billRepository.findById();
    //}
}
