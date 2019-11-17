package com.example.bank_back_end.services;

import com.example.bank_back_end.exceptions.ResourceNotFoundException;
import com.example.bank_back_end.models.Account;
import com.example.bank_back_end.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllCustomerAccounts(Long customerId) {
        return accountRepository.findAllAccountsByCustomerId(customerId);

    }

    public Account createAccountForCustomer(Long customerId, Account account) {
        account.setCustomerId(customerId);

        return accountRepository.save(account);
    }

    public Account updateAccount(Account account, Long accountId) {
        account.setId(accountId);

        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    public void verifyAccountId(Long accountId, String message) throws ResourceNotFoundException {
        Optional<Account> account = getAccountById(accountId);

        if (!account.isPresent())
            throw new ResourceNotFoundException(message);
    }
}
