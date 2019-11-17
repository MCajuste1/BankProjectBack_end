package com.example.bank_back_end.repositories;


import com.example.bank_back_end.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
