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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

    @GetMapping("")
//    @Operation(summary = "Get all bills", responses = {
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(
//                    responseCode = "200",
//            )
//    })
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<Bill>> findAll(
            HttpServletRequest req
    ) {
        final String clientReq = req.getHeader("X-FORWARDED-FOR") == null
                ? req.getRemoteAddr()
                : req.getHeader("X-FORWARDED_FOR")
                + " - " + req.getRequestURI();
        logger.info(clientReq + ": Start getting all bills");

        ApiResponse<List<Bill>> apiResponse = new ApiResponse<>();
        apiResponse.setData(billService.getALlBills());
        apiResponse.setMessage("Get all bills successfully");

        logger.info(clientReq + ": Successfully getting all bills");
        return apiResponse;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Bill> findOne(
            @PathVariable String id,
            HttpServletRequest req
    ) {
        final String clientReq = req.getRequestURL().toString() + " - " + req.getRequestURI();
        logger.info(clientReq + ": Start getting bill - id: " + id);

        ApiResponse<Bill> apiResponse = new ApiResponse<>();
        apiResponse.setData(billService.getBillById(id));
        apiResponse.setMessage("Get bill id:" + id+ " successfully");

        logger.info(clientReq + " - Successfully getting bill - id:" + id);
        return apiResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Bill> create(
            @Validated @RequestBody BillCreationRequest request,
            BindingResult bindingResult,
            HttpServletRequest req
    ) {
        final String clientReq = req.getRequestURL().toString() + " - " + req.getRequestURI();
        logger.info(clientReq + ": Start creating bill");

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

        logger.info(clientReq + ": Successfully creating bill");
        return apiResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> update(
            @PathVariable String id,
            @Validated @RequestBody BillModifyRequest data,
            BindingResult bindingResult,
            HttpServletRequest req
    ) {
        final String clientReq = req.getRequestURL().toString() + " - " + req.getRequestURI();
        logger.info(clientReq + ": Start updating bill - id:" + id);

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

        logger.info(clientReq + ": Successfully updating bill - id:" + id);
        return apiResponse;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> delete(
            @PathVariable String id,
            HttpServletRequest req
    ) {
        final String clientReq = req.getRequestURL().toString() + " - " + req.getRequestURI();
        logger.info(clientReq + ": Start deleting bill - id:" + id);

        ApiResponse<?> apiResponse = new ApiResponse();
        billService.deleteBillById(id);
        apiResponse.setMessage("Delete bill id:" + id + " successfully");

        logger.info(clientReq + ": Successfully deleting bill - id:" + id);
        return apiResponse;
    }
}
