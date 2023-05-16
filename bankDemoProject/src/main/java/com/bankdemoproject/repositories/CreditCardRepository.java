package com.bankdemoproject.repositories;

import com.bankdemoproject.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> findByCardNo(String cardNo);
}