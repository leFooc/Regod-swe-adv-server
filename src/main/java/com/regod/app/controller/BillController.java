package com.regod.app.controller;

import com.regod.app.dto.request.BillCreationRequest;
import com.regod.app.dto.response.ApiResponse;
import com.regod.app.entity.Bill;
import com.regod.app.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping
    ApiResponse<Bill> createBill(@RequestBody BillCreationRequest request) {
        ApiResponse<Bill> apiResponse = new ApiResponse<>();
        apiResponse.setResult(billService.createBill(request));
        return apiResponse;
    }
}
