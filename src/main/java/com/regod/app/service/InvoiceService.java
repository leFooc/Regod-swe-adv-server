package com.regod.app.service;

import com.regod.app.entity.Invoice;
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

    public void create() {}

    public void updateById(String id, String a) {}

    public void deleteById(String id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isEmpty()) {
            throw new NotFoundException("Invoice not found");
        }

        invoiceRepository.deleteById(id);
    }
}
