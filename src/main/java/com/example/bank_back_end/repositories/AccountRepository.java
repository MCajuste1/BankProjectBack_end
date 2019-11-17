package com.example.bank_back_end.repositories;

import com.example.bank_back_end.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllAccountsByCustomerId(Long customerId);

}
