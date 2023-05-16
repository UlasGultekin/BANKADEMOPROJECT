package com.bankdemoproject.repositories;

import com.bankdemoproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByTcNo(String tcNo);

    Optional<Customer> findByYbNo(String ybNo);

    Optional<Customer> findByTaxNo(String taxNo);

    Optional<Customer> findByCustomerNoAndPassword(String customerNo, String password);

    Optional<Customer> findByCustomerNoAndPasswordAndPhoneNo(String customerNo, String password, String phoneNo);

    long deleteByCustomerNo(String customerNo);

    List<Customer> findByTcNoOrYbNoOrTaxNo(String tcNo, String ybNo, String taxNo);











}