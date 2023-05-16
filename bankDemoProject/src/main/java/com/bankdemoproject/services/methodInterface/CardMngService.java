package com.bankdemoproject.services.methodInterface;

import com.bankdemoproject.entities.CreditCard;
import org.springframework.http.ResponseEntity;

public interface CardMngService {
  ResponseEntity<?> cardApplyTo(CreditCard creditCard);
    ResponseEntity<?>  cardDetail(String cardNo);
    ResponseEntity<?>  cardStatus(String card);
    ResponseEntity<?>  cardDelete(String card);
    ResponseEntity<?>  cardUpdate(CreditCard creditCard);
    ResponseEntity<?>  cardStatusUpdate(CreditCard creditCard);




}
