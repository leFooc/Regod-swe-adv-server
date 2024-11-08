package com.regod.app.service;

import com.regod.app.dto.request.CreateInvoiceDto;
import com.regod.app.dto.request.ModifyOrderDto;
import com.regod.app.entity.Invoice;
import com.regod.app.repositories.BillRepository;
import com.regod.app.repositories.InvoiceRepository;
import com.regod.app.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private BillRepository billRepository;

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice find(String id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isEmpty()) {
            throw new NotFoundException("Invoice not found");
        }

        return invoice.get();
    }

    public Invoice create(CreateInvoiceDto data) {
        Invoice record = new Invoice(
                data.getPaidDate(),
                data.getPaidAmount(),
                data.getImgURL()
        );

        return invoiceRepository.save(record);
    }

    public void updateById(String id, ModifyOrderDto data) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isEmpty()) {
            throw new NotFoundException("Invoice not found");
        }

        Invoice res = invoice.get();
        if (data.getPaidDate() != null)
            res.setPaidDate(data.getPaidDate());
        if (data.getPaidAmount() != null)
            res.setPaidAmount(data.getPaidAmount());
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
