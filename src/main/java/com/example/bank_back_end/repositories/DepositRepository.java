package com.example.bank_back_end.repositories;

import com.example.bank_back_end.models.Deposit;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

	List<Deposit> findAllByPayeeId(Long accountId);

}
