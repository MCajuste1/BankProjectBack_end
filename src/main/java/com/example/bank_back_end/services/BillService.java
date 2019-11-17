package com.example.bank_back_end.services;

import com.example.bank_back_end.exceptions.ResourceNotFoundException;
import com.example.bank_back_end.models.Account;
import com.example.bank_back_end.models.Bill;
import com.example.bank_back_end.models.Deposit;
import com.example.bank_back_end.repositories.AccountRepository;
import com.example.bank_back_end.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private BillRepository billRepository;

    public Optional<Bill> getBill(Long billId) {
        return billRepository.findById(billId);
    }

    public List<Bill> getAllBillsByAccountId(Long accountId) {
        return billRepository.findAllByAccountId(accountId);
    }

    public List<Bill> getAllBillsByCustomerId(Long customerId) {
        List<Account> customerAccounts = accountRepository.findAllAccountsByCustomerId(customerId);
        List<Bill> allCustomerBills = new ArrayList<>();

        customerAccounts.forEach(account -> allCustomerBills.addAll(getAllBillsByAccountId(account.getId())));

        return allCustomerBills;
    }

    public Bill createBill(Bill bill, Long accountId) {
        bill.setAccountId(accountId);

        return billRepository.save(bill);
    }

    public Bill updateBill(Bill bill, Long accountId) {
        bill.setId(accountId);
        return billRepository.save(bill);
    }

    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    public void verifyBill(Long billId, String message) {
        Optional<Bill> bill = getBill(billId);

        if( !bill.isPresent())
            throw new ResourceNotFoundException(message);
    }
}
