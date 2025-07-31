package com.onec.bms.customerregistration.repository;

import com.onec.bms.customerregistration.model.Customer;
import com.onec.bms.customerregistration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {



//    public Customer findCustomerById(Long id) {
//        //connect to h2 database and retrieve customer by id
//        // This is a placeholder for the actual database retrieval logic
//        return null; //
//            // Replace with actual retrieval logic
//
//    }
//    public Customer saveCustomer(Customer customer) {
//        return customerService.registerCustomer(customer);
//    }
//    public Customer updateCustomer(Customer customer) {
//        return customerService.updateCustomer(customer);
//    }
//    public void deleteCustomer(Long id) {
//        customerService.deleteCustomer(id);
//    }
//    public List<Customer> findAllCustomers() {
//        // Assuming there's a method in CustomerService to get all customers
//        return customerService.getAllCustomers();
//    }
}
