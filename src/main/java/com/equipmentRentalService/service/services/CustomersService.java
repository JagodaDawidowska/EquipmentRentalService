package com.equipmentRentalService.service.services;

import com.equipmentRentalService.service.api.dto.request.CustomerRequest;
import com.equipmentRentalService.service.data.entities.Customer;
import com.equipmentRentalService.service.data.repos.CustomersRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public void addCustomer(CustomerRequest customerRequest){

        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customer.setEmail(customerRequest.getEmail());
        customer.setPassword(customerRequest.getPassword());

        customersRepository.save(customer);
    }
}