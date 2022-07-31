package com.jdawidowska.service.api.controllers;

import com.jdawidowska.service.services.CustomersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final CustomersService customersService;

    public LoginController(CustomersService customersService) {
        this.customersService = customersService;
    }
}