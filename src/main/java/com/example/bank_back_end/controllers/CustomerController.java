package com.example.bank_back_end.controllers;

import com.example.bank_back_end.models.Customer;
import com.example.bank_back_end.models.errors.AdvancedResponse;
import com.example.bank_back_end.services.AccountService;
import com.example.bank_back_end.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	@GetMapping("/accounts/{accountId}/customer")
	public ResponseEntity<?> getAccountOwner(@PathVariable Long accountId) {
		accountService.verifyAccountId(accountId, "Error fetching account");

		Optional<Customer> customer = customerService.getAccountOwner(accountId);

		return new ResponseEntity<>(
				AdvancedResponse.Create(customer, "Success", HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		return new ResponseEntity<>(
				AdvancedResponse.Create(allCustomers, "Success", HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long customerId) {
		customerService.verifyCustomerId(customerId,"error fetching customer");

		Optional<Customer> customer = customerService.getCustomerById(customerId);
		return new ResponseEntity<>(
				AdvancedResponse.Create(customer, "Success", HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	//Creates Customers
	@PostMapping("/customers")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
		Customer newCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(
				AdvancedResponse.Create(newCustomer, "Customer created", HttpStatus.CREATED.value()),
				HttpStatus.CREATED);
	}

	//Update Customer By Id
	@PutMapping("/customers/{customerId}")
	public ResponseEntity<?> updateAccount(@RequestBody Customer customer, @PathVariable Long customerId) {
		customerService.verifyCustomerId(customerId, "Customer account updated");
		Customer customer1 = customerService.updateCustomer(customer, customerId);
		return new ResponseEntity<>(
				AdvancedResponse.Create(customer1, "Customer account updated", HttpStatus.ACCEPTED.value()),
				HttpStatus.OK);
	}

}
