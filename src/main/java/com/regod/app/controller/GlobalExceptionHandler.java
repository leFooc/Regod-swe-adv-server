package com.regod.app.controller;

import com.regod.app.dto.response.ApiResponse;
import com.regod.app.dto.response.ResponseCode;
import com.regod.app.utils.exceptions.BadRequestException;
import com.regod.app.utils.exceptions.InternalServerErrorException;
import com.regod.app.utils.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private String genLog(
            RuntimeException e,
            HttpServletRequest req
    ) {
        return req.getRequestURL() + " - " + req.getRequestURI() + ": " + e.getMessage();
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse<?> handleServerError (
            RuntimeException e,
            HttpServletRequest req
    ) {
        logger.error(req.getRequestURI());
        ApiResponse<?> res = new ApiResponse<>();
        res.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        res.setMessage("Erm...");
        return res;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<?> handleBadRequest (
            RuntimeException e,
            HttpServletRequest req
    ) {
        logger.warn(genLog(e, req));
        ApiResponse<?> res = new ApiResponse<>();
        res.setCode(ResponseCode.BAD_REQUEST);
        res.setMessage(e.getMessage());
        return res;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<?> handleNotFound (
            RuntimeException e,
            HttpServletRequest req
    ) {
        logger.warn(genLog(e, req));
        ApiResponse<?> res = new ApiResponse<>();
        res.setCode(ResponseCode.NOT_FOUND);
        res.setMessage(e.getMessage());
        return res;
    }
}


