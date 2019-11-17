package com.example.bank_back_end.repositories;

import com.example.bank_back_end.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal,Long> {

        List<Withdrawal> findAllByPayerId(Long accountId);

}
