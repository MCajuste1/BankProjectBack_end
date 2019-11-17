package com.example.bank_back_end.services;

import com.example.bank_back_end.exceptions.ResourceNotFoundException;
import com.example.bank_back_end.models.Deposit;
import com.example.bank_back_end.repositories.DepositRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

	@Autowired
	DepositRepository depositRepository;

	public List<Deposit> getDepositsForAccountId(Long accountId) {

		return new ArrayList<>(depositRepository.findAllByPayeeId(accountId));
	}

	public Optional<Deposit> getDeposit(Long depositId) {
		return depositRepository.findById(depositId);
	}

	public Deposit createDeposit(Deposit deposit, Long accountId) {
		deposit.setPayeeId(accountId);

		return depositRepository.save(deposit);
	}

	public Deposit updateDeposit(Deposit deposit, Long depositId) {
		deposit.setId(depositId);

		return depositRepository.save(deposit);
	}

	public void deleteDeposit(Long depositId) {
		depositRepository.deleteById(depositId);
	}

	public void verifyDepositId(Long withdrawalId, String message) throws ResourceNotFoundException {
		Optional<Deposit> deposit = getDeposit(withdrawalId);

		if(!deposit.isPresent())
			throw new ResourceNotFoundException(message);
	}
}
