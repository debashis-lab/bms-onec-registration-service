package com.onec.bms.customerregistration.service.impl;

import com.onec.bms.customerregistration.dto.LoginRequest;
import com.onec.bms.customerregistration.dto.RegistrationRequest;
import com.onec.bms.customerregistration.model.Customer;
import com.onec.bms.customerregistration.repository.CustomerRepository;
import com.onec.bms.customerregistration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(RegistrationRequest registrationRequest) {
        // Check if customer already exists with this email
        if (customerRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new RuntimeException("Customer with email " + registrationRequest.getEmail() + " already exists");
        }

        // Create new customer
        Customer customer = new Customer();
        customer.setName(registrationRequest.getName());
        customer.setEmail(registrationRequest.getEmail());
        customer.setPassword(registrationRequest.getPassword()); // In production, this should be hashed
        customer.setPhoneNumber(registrationRequest.getPhoneNumber());
        customer.setAddress(registrationRequest.getAddress());
        customer.setCity(registrationRequest.getCity());
        customer.setState(registrationRequest.getState());
        customer.setZipCode(registrationRequest.getZipCode());
        customer.setCountry(registrationRequest.getCountry());

        return customerRepository.save(customer);
    }

    @Override
    public Customer loginCustomer(LoginRequest loginRequest) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(loginRequest.getEmail());
        
        if (customerOptional.isEmpty()) {
            throw new RuntimeException("Customer with email " + loginRequest.getEmail() + " not found");
        }

        Customer customer = customerOptional.get();
        
        // In production, you should hash the password and compare hashed values
        if (!customer.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return customer;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (!customerRepository.existsById(customer.getId())) {
            throw new RuntimeException("Customer with id " + customer.getId() + " not found");
        }
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer with id " + id + " not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
