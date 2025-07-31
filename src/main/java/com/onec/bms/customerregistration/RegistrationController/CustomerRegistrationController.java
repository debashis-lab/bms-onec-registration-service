package com.onec.bms.customerregistration.controller;

import com.onec.bms.customerregistration.model.Customer;
import com.onec.bms.customerregistration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerRegistrationController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }
}
