package com.regod.app.controller;

import com.regod.app.dto.request.CreateOrderDto;
import com.regod.app.dto.request.ModifyRequestDto;
import com.regod.app.dto.response.ApiResponse;
import com.regod.app.dto.response.ResponseCode;
import com.regod.app.entity.Order;
import com.regod.app.service.OrderService;
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
@RequestMapping("/request")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<Order>> findAll() {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start getting all requests"));

        ApiResponse<List<Order>> res = new ApiResponse<>();
        res.setData(orderService.findAll());
        res.setMessage("Get all requests successfully");

        logger.info(reqId.concat(": Successfully getting all requests"));
        return res;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Order> findOne(
            @PathVariable String id
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start getting request - id: " + id));

        ApiResponse<Order> res = new ApiResponse<>();
        res.setData(orderService.find(id));
        res.setMessage("Get request id:" + id + " successfully");

        logger.info(reqId.concat(": Successfully getting request - id: " + id));
        return res;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Order> create(
            @Validated @RequestBody CreateOrderDto data,
            BindingResult bindingResult
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start creating new request"));

        if (bindingResult.hasErrors()) {
            String msg = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            throw new BadRequestException(msg);
        }

        ApiResponse<Order> res = new ApiResponse<>();
        res.setData(orderService.create(data));
        res.setMessage("Create request successfully");
        res.setCode(ResponseCode.CREATED);

        logger.info(reqId.concat(": Successfully creating request invoice"));
        return res;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> update(
            @PathVariable String id,
            @Validated @RequestBody ModifyRequestDto data,
            BindingResult bindingResult
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start updating request - id: " + id));

        if (bindingResult.hasErrors()) {
            String msg = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            throw new BadRequestException(msg);
        }

        ApiResponse<?> res = new ApiResponse<>();
        orderService.updateById(id, data);
        res.setMessage("Update request id:" + id + " successfully");

        logger.info(reqId.concat(": Successfully updating request - id: " + id));
        return res;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> delete(
            @PathVariable String id
    ) {
        final String reqId = UUID.randomUUID().toString();
        logger.info(reqId.concat(": Start deleting request - id: " + id));

        ApiResponse<?> res = new ApiResponse<>();
        orderService.deleteById(id);
        res.setMessage("Delete request id:" + id + " successfully");

        logger.info(reqId.concat(": Successfully deleting request - id: " + id));
        return res;
    }
}
