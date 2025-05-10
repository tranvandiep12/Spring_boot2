package com.techzen.academy_n0325c1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String greeting(@RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "Quảng Nam") String address){
        return "hello " + name + ","+ address;
    }
}
