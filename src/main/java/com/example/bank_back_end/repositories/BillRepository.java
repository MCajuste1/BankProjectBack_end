package com.example.bank_back_end.repositories;

import com.example.bank_back_end.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
	List<Bill> findAllByAccountId(Long accountId);
}
