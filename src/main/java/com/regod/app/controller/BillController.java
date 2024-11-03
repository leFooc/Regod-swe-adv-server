package com.regod.app.controller;

import com.regod.app.dto.request.BillCreationRequest;
import com.regod.app.dto.response.ApiResponse;
import com.regod.app.entity.Bill;
import com.regod.app.service.BillService;
import com.regod.app.utils.exceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;



    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<Bill>> findAll() {
        try {
            ApiResponse<List<Bill>> apiResponse = new ApiResponse<>();
            apiResponse.setData(billService.getALlBills());
            apiResponse.setMessage("Get all bills successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Bill> findOne(
            @PathVariable String id
    ) {
        try {
            ApiResponse<Bill> apiResponse = new ApiResponse<>();
            apiResponse.setData(billService.getBillById(id));
            apiResponse.setMessage("Get bill id:" + id+ " successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Bill> createBill(@RequestBody BillCreationRequest request) {
        try {
            ApiResponse<Bill> apiResponse = new ApiResponse<>();
            apiResponse.setData(billService.createBill(request));
            apiResponse.setMessage("Create bill successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void update(

            @PathVariable String id,


            @RequestBody Integer data
    ) {

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete(
            @PathVariable String id
    ) {
        try {
            ApiResponse apiResponse = new ApiResponse();
            billService.deleteBillById(id);
            apiResponse.setMessage("Delete bill id:" + id + " successfully");
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }
}
