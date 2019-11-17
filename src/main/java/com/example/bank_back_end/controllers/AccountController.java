package com.example.bank_back_end.controllers;

import com.example.bank_back_end.models.Account;
import com.example.bank_back_end.models.errors.AdvancedResponse;
import com.example.bank_back_end.models.errors.SimpleResponse;

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
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/accounts")
	public ResponseEntity<?> getAllAccounts() {
		List<Account> allAccounts = accountService.getAllAccounts();
		return new ResponseEntity<>(
				AdvancedResponse.Create(allAccounts, "Success", HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	//Gets Account By Id
	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
		accountService.verifyAccountId(accountId, "error fetching account");
		Optional<Account> account = accountService.getAccountById(accountId);

		return new ResponseEntity<>(
				AdvancedResponse.Create(account, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
	}

	//Gets All Customer Accounts
	@GetMapping("/customers/{customerId}/accounts")
	public ResponseEntity<?> getAllCustomerAccounts(@PathVariable Long customerId) {
	    customerService.verifyCustomerId(customerId, "Error fetching customer accounts");

	    List<Account> customerAccounts = accountService.getAllCustomerAccounts(customerId);

		return new ResponseEntity<>(
				AdvancedResponse.Create(customerAccounts, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
	}

	//Creates Accounts
	@PostMapping("/customers/{customerId}/accounts")
	public ResponseEntity<?> createAccount(@PathVariable Long customerId, @Valid @RequestBody Account account) {
		customerService.verifyCustomerId(customerId, "Error fetching customer to create account for");

	    Account account1 = accountService.createAccountForCustomer(customerId, account);

	    return new ResponseEntity<>(
	            AdvancedResponse.Create(account1, "Account created", HttpStatus.CREATED.value()),
			    HttpStatus.CREATED);
	}

	@PutMapping("/accounts/{accountId}")
	public ResponseEntity<?> updateAccount(@Valid @RequestBody Account account, @PathVariable Long accountId) {
		accountService.verifyAccountId(accountId, "Error");
	    accountService.updateAccount(account, accountId);

		return new ResponseEntity<>(
				SimpleResponse.Create("Customer Account updated", HttpStatus.ACCEPTED.value()),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/accounts/{accountId}")
	public ResponseEntity<?> deleteAccountById(@PathVariable Long accountId) {
		accountService.verifyAccountId(accountId, "Account does not exist");

		accountService.deleteAccount(accountId);
		return new ResponseEntity<>(
				SimpleResponse.Create("Account successfully deleted", HttpStatus.OK.value()),
				HttpStatus.OK);
	}

}
