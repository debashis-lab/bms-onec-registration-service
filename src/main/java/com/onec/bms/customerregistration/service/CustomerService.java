package com.onec.bms.customerregistration.service;

import com.onec.bms.customerregistration.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer registerCustomer(Customer customer);

    public Customer getCustomerById(Long id);

    public Customer updateCustomer(Customer customer);

    public void deleteCustomer(Long id);

    List<Customer> getAllCustomers();
}
