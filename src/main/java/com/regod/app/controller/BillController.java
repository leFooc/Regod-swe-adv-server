package com.regod.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@RestController
@RequestMapping("/bills")
public class BillController {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    List<> findAll() {
        return new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
    }

    @GetMapping("/{:id}")
    @ResponseStatus(HttpStatus.OK)
    Integer findOne(

            @PathVariable Integer id
    ) {
        return id;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create() {

    }


    @PutMapping("/{:id}")
    @ResponseStatus(HttpStatus.OK)
    void update(

            @PathVariable Integer id,


            @RequestBody Integer data
    ) {

    }


    @DeleteMapping("/{:id}")
    @ResponseStatus(HttpStatus.OK)
    void delete(

            @PathVariable Integer id
    ) {

    }
}
