package com.bankdemoproject.services.impl;

import com.bankdemoproject.entities.CreditCard;
import com.bankdemoproject.entities.Customer;
import com.bankdemoproject.enums.REnum;
import com.bankdemoproject.repositories.CreditCardRepository;
import com.bankdemoproject.repositories.CustomerRepository;
import com.bankdemoproject.services.methodInterface.CardMngService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardMngServiceImpl implements CardMngService {
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;


    @Override
    public ResponseEntity<?> cardApplyTo(CreditCard creditCard) {
        Map<REnum,Object> hashMAp=new LinkedHashMap<>();
      try {


        Optional<Customer> optionalCreditCard=customerRepository.findById(creditCard.getCustomer_id());

        if (optionalCreditCard.isPresent()){
            Boolean status=false;
            creditCard.setCardStatus(status);
            creditCardRepository.save(creditCard);
            hashMAp.put(REnum.status,true);
            hashMAp.put(REnum.result,creditCard);
            return new ResponseEntity<>(hashMAp, HttpStatus.OK);
        }else {
            hashMAp.put(REnum.status,false);
            hashMAp.put(REnum.result,"Başvuru Yapılacak Müşteri Bulunamadı");
            return new ResponseEntity<>(hashMAp,HttpStatus.NOT_FOUND);
        }
      }catch (Exception e){
          hashMAp.put(REnum.status,false);
          hashMAp.put(REnum.result,e.getMessage());
          return new ResponseEntity<>(hashMAp,HttpStatus.BAD_REQUEST);
      }
    }

    @Override
    public ResponseEntity<?> cardDetail(String cardNo) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<CreditCard> optionalCreditCard=creditCardRepository.findByCardNo(cardNo);
       try{


        if (optionalCreditCard.isPresent()){
            hashMap.put(REnum.status,true);
            hashMap.put(REnum.result,optionalCreditCard);
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }else {
            hashMap.put(REnum.status,false);
            hashMap.put(REnum.result,"Uygun Kredi Kartı Bulunamdı");
            return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
        } }catch (Exception e){
           hashMap.put(REnum.status,false);
           hashMap.put(REnum.result,e.getMessage());
           return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    public ResponseEntity<?> cardStatus(String card) {
        return null;
    }

    @Override
    public ResponseEntity<?> cardDelete(String card) {
        return null;
    }

    @Override
    public ResponseEntity<?> cardUpdate(CreditCard creditCard) {
        return null;
    }

    @Override
    public ResponseEntity<?> cardStatusUpdate(CreditCard creditCard) {
        return null;
    }
}
