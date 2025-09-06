package com.onec.bms.customerregistration.RegistrationController;

import com.onec.bms.customerregistration.dto.ApiResponse;
import com.onec.bms.customerregistration.dto.LoginRequest;
import com.onec.bms.customerregistration.dto.RegistrationRequest;
import com.onec.bms.customerregistration.model.Customer;
import com.onec.bms.customerregistration.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Registration", description = "APIs for customer registration and authentication")
public class CustomerRegistrationController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    @Operation(summary = "Register a new customer", description = "Create a new customer account with provided details")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Customer registered successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Customer already exists")
    })
    public ResponseEntity<ApiResponse<Customer>> registerCustomer(@Valid @RequestBody RegistrationRequest registrationRequest) {
        try {
            Customer customer = customerService.registerCustomer(registrationRequest);
            ApiResponse<Customer> response = ApiResponse.success("Customer registered successfully", customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            ApiResponse<Customer> response = ApiResponse.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Customer login", description = "Authenticate customer with email and password")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Login successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<ApiResponse<Customer>> loginCustomer(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Customer customer = customerService.loginCustomer(loginRequest);
            // Remove password from response for security
            customer.setPassword(null);
            ApiResponse<Customer> response = ApiResponse.success("Login successful", customer);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Customer> response = ApiResponse.error(e.getMessage());
            HttpStatus status = e.getMessage().contains("not found") ? HttpStatus.NOT_FOUND : HttpStatus.UNAUTHORIZED;
            return ResponseEntity.status(status).body(response);
        }
    }
}
