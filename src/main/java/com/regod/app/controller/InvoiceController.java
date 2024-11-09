package com.regod.app.controller;

import com.regod.app.dto.response.ApiResponse;
import com.regod.app.utils.exceptions.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Integer> findAll() {
        try {
            ApiResponse res = new ApiResponse();
            res.setData(1);
            return res;
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Integer> findOne(
            @PathVariable String id
    ) {
        try {
            ApiResponse res = new ApiResponse();
            res.setData(1);
            return res;
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Integer> create(
            @RequestBody String data
    ) {
        try {
            ApiResponse res = new ApiResponse();
            res.setData(1);
            return res;
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Integer> update(
            @PathVariable String id,
            @RequestBody String data
    ) {
        try {
            ApiResponse res = new ApiResponse();
            res.setData(1);
            return res;
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse delete(
            @PathVariable String id
    ) {
        try {
            ApiResponse res = new ApiResponse();
            res.setData(1);
            return res;
        } catch (Exception e) {
            throw new InternalServerErrorException("");
        }
    }
}
