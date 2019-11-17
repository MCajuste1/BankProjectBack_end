package com.example.bank_back_end.services;

import com.example.bank_back_end.exceptions.ResourceNotFoundException;
import com.example.bank_back_end.models.Account;
import com.example.bank_back_end.models.Customer;
import com.example.bank_back_end.repositories.AccountRepository;
import com.example.bank_back_end.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
   CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getAccountOwner(Long accountId) {
        Optional<Customer> customer = Optional.empty();
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            customer = customerRepository.findById(account.get().getCustomerId());

            return customer;
        }
        return customer;
    }

    public Optional<Customer> getCustomerById(Long customerId) {

        return customerRepository.findById(customerId);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer, Long customerId) {
        verifyCustomerId(customerId, "Customer account updated");

        customer.setId(customerId);

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public void verifyCustomerId(Long customerId, String message) throws ResourceNotFoundException {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (!customer.isPresent())
            throw new ResourceNotFoundException(message);
    }
}
