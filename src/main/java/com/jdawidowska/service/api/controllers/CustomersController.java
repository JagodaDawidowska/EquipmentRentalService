package com.jdawidowska.service.api.controllers;

import com.jdawidowska.service.api.dto.request.CustomerRequest;
import com.jdawidowska.service.services.CustomersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @PostMapping
    public void addCustomer(@RequestBody CustomerRequest customerRequest){
        customersService.addCustomer(customerRequest);
    }
}
