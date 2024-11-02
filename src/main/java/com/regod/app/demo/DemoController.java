package com.regod.app.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping("/hello")
    public String getHello() {
        return "Hello world";
    }
}
