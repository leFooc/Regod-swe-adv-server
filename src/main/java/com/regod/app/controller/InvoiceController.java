package com.regod.app.controller;

import com.regod.app.dto.request.CreateInvoiceDto;
import com.regod.app.dto.request.ModifyInvoiceDto;
import com.regod.app.dto.response.ApiResponse;
import com.regod.app.dto.response.InvoiceDetailResponse;
import com.regod.app.dto.response.InvoiceResponse;
import com.regod.app.dto.response.ResponseCode;
import com.regod.app.entity.Invoice;
import com.regod.app.service.InvoiceService;
import com.regod.app.utils.exceptions.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    private final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<InvoiceResponse>> findAll() {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start getting all invoices"));

        ApiResponse<List<InvoiceResponse>> res = new ApiResponse<>();
        res.setData(invoiceService.findAll());
        res.setMessage("Get all invoice successfully");

        logger.info(reqId.concat(": Successfully getting all invoices"));
        return res;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<InvoiceDetailResponse> findOne(
            @PathVariable String id
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start getting invoice - id: " + id));

        ApiResponse<InvoiceDetailResponse> res = new ApiResponse<>();
        res.setData(invoiceService.find(id));
        res.setMessage("Get invoice id:" + id + " successfully");

        logger.info(reqId.concat(": Successfully getting invoice - id: " + id));
        return res;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<InvoiceResponse> create(
            @RequestParam String id,
            @Validated @RequestBody CreateInvoiceDto data,
            BindingResult bindingResult
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start creating new invoice"));

        if (bindingResult.hasErrors()) {
            String msg = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            throw new BadRequestException(msg);
        }

        ApiResponse<InvoiceResponse> res = new ApiResponse<>();
        res.setData(invoiceService.create(id, data));
        res.setMessage("Create invoice successfully");
        res.setCode(ResponseCode.CREATED);

        logger.info(reqId.concat(": Successfully creating new invoice"));
        return res;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> update(
            @PathVariable String id,
            @Validated @RequestBody ModifyInvoiceDto data,
            BindingResult bindingResult
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start updating invoice - id: " + id));

        if (bindingResult.hasErrors()) {
            String msg = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            throw new BadRequestException(msg);
        }

        ApiResponse<?> res = new ApiResponse<>();
        invoiceService.updateById(id, data);
        res.setMessage("Update bill id:" + id + " successfully");

        logger.info(reqId.concat(": Successfully updating invoice - id: " + id));
        return res;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> delete(
            @PathVariable String id
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start deleting invoice - id: " + id));

        ApiResponse<?> res = new ApiResponse<>();
        invoiceService.deleteById(id);
        res.setMessage("Delete bill id:" + id + " successfully");

        logger.info(reqId.concat(": Successfully deleting invoice - id: " + id));
        return res;
    }
}
