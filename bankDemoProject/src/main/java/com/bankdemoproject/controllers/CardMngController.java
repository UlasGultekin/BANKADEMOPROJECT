package com.bankdemoproject.controllers;

import com.bankdemoproject.entities.CreditCard;
import com.bankdemoproject.services.methodInterface.CardMngService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/card-mng-v1")
public class CardMngController {
    private final CardMngService cardMngService;

    @PostMapping("/cardApply")
    ResponseEntity cardApply(@RequestBody CreditCard creditCard){
        return cardMngService.cardApplyTo(creditCard);
    }
    @GetMapping("/cardDetail")
    ResponseEntity cardDetail(String cardNo){
        return cardMngService.cardDetail(cardNo);
    }

}
