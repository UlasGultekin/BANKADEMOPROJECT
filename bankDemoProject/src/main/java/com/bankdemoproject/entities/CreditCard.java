package com.bankdemoproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String customer_id;
    private String cardNo;
    private String cardStartDate;
    private String cardEndDate;
    private String cardCvc1;
    private String cardCvc2;
    private String cardLimit;
    private String cardDept;
    private String cardCurrentLimit;
    private String cutOffDate;
    private String lastPaymentDate;

}
