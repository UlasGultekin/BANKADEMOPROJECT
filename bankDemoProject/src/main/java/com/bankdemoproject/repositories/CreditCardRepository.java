package com.bankdemoproject.repositories;

import com.bankdemoproject.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}