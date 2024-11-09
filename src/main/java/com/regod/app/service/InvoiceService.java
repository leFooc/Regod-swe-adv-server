package com.regod.app.service;

import com.regod.app.dto.request.BillModifyRequest;
import com.regod.app.dto.request.CreateInvoiceDto;
import com.regod.app.dto.request.ModifyInvoiceDto;
import com.regod.app.dto.response.BillDetailResponse;
import com.regod.app.dto.response.InvoiceDetailResponse;
import com.regod.app.dto.response.InvoiceResponse;
import com.regod.app.entity.Bill;
import com.regod.app.entity.Invoice;
import com.regod.app.repositories.BillRepository;
import com.regod.app.repositories.InvoiceRepository;
import com.regod.app.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private BillService billService;

    public List<InvoiceResponse> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(invoice -> {
                Bill bill = billService.getBillById(invoice.getBillID());
                return new InvoiceResponse(
                        invoice.getBillID(),
                        invoice.getPaidDate(),
                        invoice.getImgURL(),

                        bill.getBillName(),
                        bill.getDepartmentName(),
                        bill.getCreateDate(),
                        bill.getDueDate(),
                        bill.getStatus(),

                        bill.getStatus(),
                        bill.getTotalCost(),
                        bill.getDeposited()
                );
            }
        )
        .collect(Collectors.toList());
    }

    public InvoiceDetailResponse find(String id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isEmpty()) {
            throw new NotFoundException("Invoice not found");
        }

        BillDetailResponse bill = billService.getBillDetail(invoice.get().getBillID());
        return new InvoiceDetailResponse(
                invoice.get().getBillID(),
                invoice.get().getPaidDate(),
                invoice.get().getImgURL(),

                bill.getBillName(),
                bill.getDepartmentName(),
                bill.getCreateDate(),
                bill.getDueDate(),
                bill.getStatus(),

                bill.getStatus(),
                bill.getTotalCost(),
                bill.getDeposited(),

                bill.getProducts()
        );
    }

    public InvoiceResponse create(String id, CreateInvoiceDto data) {
        BillModifyRequest updateBill = new BillModifyRequest();
        updateBill.setStatus("PAID");
        billService.updateBillById(id, updateBill);

        Invoice record = new Invoice();
        record.setBillID(id);
        record.setPaidDate(data.getPaidDate());
        record.setImgURL(data.getImgURL());
        invoiceRepository.save(record);
        return new InvoiceResponse(
                record.getBillID(),
                record.getPaidDate(),
                record.getImgURL(),

                updateBill.getBillName(),
                updateBill.getDepartmentName(),
                updateBill.getCreateDate(),
                updateBill.getDueDate(),
                updateBill.getStatus(),

                updateBill.getStatus(),
                updateBill.getTotalCost(),
                updateBill.getDeposited()
        );
    }

    public void updateById(String id, ModifyInvoiceDto data) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isEmpty()) {
            throw new NotFoundException("Invoice not found");
        }

        Invoice res = invoice.get();
        if (data.getPaidDate() != null)
            res.setPaidDate(data.getPaidDate());
        if (data.getImgURL() != null)
            res.setImgURL(data.getImgURL());

        invoiceRepository.save(res);
    }

    public void deleteById(String id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isEmpty()) {
            throw new NotFoundException("Invoice not found");
        }

        invoiceRepository.deleteById(id);
    }
}
