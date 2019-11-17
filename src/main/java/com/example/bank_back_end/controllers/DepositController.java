package com.example.bank_back_end.controllers;

import com.example.bank_back_end.models.Deposit;
import com.example.bank_back_end.models.errors.AdvancedResponse;
import com.example.bank_back_end.services.AccountService;
import com.example.bank_back_end.services.DepositService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DepositController {

	@Autowired
	DepositService depositService;

	@Autowired
	private AccountService accountService;
//get all deposit
	@GetMapping("/accounts/{accountId}/deposits")
	public ResponseEntity<?> getAllDeposits (@PathVariable Long accountId) {
		accountService.verifyAccountId(accountId, "Account not found");

		List<Deposit> deposits = depositService.getDepositsForAccountId(accountId);

		return new ResponseEntity<>(
				AdvancedResponse.Create(deposits, "Success", HttpStatus.OK.value()),
				HttpStatus.OK);
	}

//get all by id
	@GetMapping("/deposits/{depositId}")
	public ResponseEntity<?> getDepositById(@PathVariable Long depositId) {
		depositService.verifyDepositId(depositId, "Error fetching deposit with id: " + depositId);

		Optional<Deposit> deposit = depositService.getDeposit(depositId);

		return new ResponseEntity<>(
				AdvancedResponse.Create(deposit, "Success", HttpStatus.OK.value()),
				HttpStatus.OK);
	}
//create deposit
	@PostMapping("/accounts/{accountId}/deposits")
	public ResponseEntity<?> createDeposit(@Valid  @RequestBody Deposit deposit, @PathVariable Long accountId) {
		accountService.verifyAccountId(accountId, "Error creating deposit: Account not found");

		Deposit newDeposit = depositService.createDeposit(deposit, accountId);

		return new ResponseEntity<>(
				AdvancedResponse.Create(newDeposit, "Credited deposit and added it to the account", HttpStatus.CREATED.value()),
				HttpStatus.CREATED);
	}
//update specific deposit
	@PutMapping("/deposits/{depositId}")
	public ResponseEntity<?> updateDeposit(@Valid @RequestBody Deposit deposit, @PathVariable Long depositId){
		depositService.verifyDepositId(depositId, "Deposit id doesn't exist");

		Deposit update = depositService.updateDeposit(deposit, depositId);

		return new ResponseEntity<>(
				AdvancedResponse.Create(update, "Accepted deposit modification", HttpStatus.ACCEPTED.value()),
				HttpStatus.ACCEPTED);
	}
//delete by id
	@DeleteMapping("/deposits/{depositId}")
	public ResponseEntity<?> deleteDepositById(@PathVariable Long depositId) {
		depositService.verifyDepositId(depositId, "This id does not exist in deposits");
		depositService.deleteDeposit(depositId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
