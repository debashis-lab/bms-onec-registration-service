package com.onec.bms.customerregistration.service.impl;

import com.onec.bms.customerregistration.model.Customer;
import com.onec.bms.customerregistration.repository.CustomerRepository;
import com.onec.bms.customerregistration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository    customerRepository;
    @Override
    public Customer registerCustomer(Customer customer) {
        Customer customerData = customerRepository.saveCustomer(customer);
        return customerData;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }
}
