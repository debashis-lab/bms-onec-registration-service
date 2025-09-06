package com.onec.bms.customerregistration.service;

import com.onec.bms.customerregistration.dto.LoginRequest;
import com.onec.bms.customerregistration.dto.RegistrationRequest;
import com.onec.bms.customerregistration.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer registerCustomer(RegistrationRequest registrationRequest);
    Customer loginCustomer(LoginRequest loginRequest);
    Customer getCustomerById(Long id);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long id);
    List<Customer> getAllCustomers();
}
