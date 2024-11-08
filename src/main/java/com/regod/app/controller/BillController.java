package com.regod.app.controller;

import com.regod.app.dto.request.BillCreationRequest;
import com.regod.app.dto.request.BillModifyRequest;
import com.regod.app.dto.response.ApiResponse;
import com.regod.app.entity.Bill;
import com.regod.app.service.BillService;
import com.regod.app.utils.exceptions.BadRequestException;
import com.regod.app.utils.exceptions.InternalServerErrorException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<Bill>> findAll() {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start getting all bills"));

        ApiResponse<List<Bill>> apiResponse = new ApiResponse<>();
        apiResponse.setData(billService.getALlBills());
        apiResponse.setMessage("Get all bills successfully");

        logger.info(reqId.concat(": Successfully getting all bills"));
        return apiResponse;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Bill> findOne(
            @PathVariable String id
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start getting bill - id: " + id));

        ApiResponse<Bill> apiResponse = new ApiResponse<>();
        apiResponse.setData(billService.getBillById(id));
        apiResponse.setMessage("Get bill id:" + id+ " successfully");

        logger.info(reqId.concat(": Successfully getting bill - id:" + id));
        return apiResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Bill> create(
            @Validated @RequestBody BillCreationRequest request,
            BindingResult bindingResult
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start creating bill"));

        if (bindingResult.hasErrors()) {
            String msg = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            throw new BadRequestException(msg);
        }

        ApiResponse<Bill> apiResponse = new ApiResponse<>();
        apiResponse.setData(billService.createBill(request));
        apiResponse.setMessage("Create bill successfully");

        logger.info(reqId.concat(": Successfully creating bill"));
        return apiResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> update(
            @PathVariable String id,
            @Validated @RequestBody BillModifyRequest data,
            BindingResult bindingResult
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start updating bill - id:" + id));

        if (bindingResult.hasErrors()) {
            String msg = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            throw new BadRequestException(msg);
        }

        ApiResponse<?> apiResponse = new ApiResponse<>();
        billService.updateBillById(id, data);
        apiResponse.setMessage("Update bill id:" + id+ " successfully");

        logger.info(reqId.concat(": Successfully updating bill - id:" + id));
        return apiResponse;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> delete(
            @PathVariable String id
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start deleting bill - id:" + id));

        ApiResponse<?> apiResponse = new ApiResponse();
        billService.deleteBillById(id);
        apiResponse.setMessage("Delete bill id:" + id + " successfully");

        logger.info(reqId.concat(": Successfully deleting bill - id:" + id));
        return apiResponse;
    }
}
