package com.bankdemoproject.services.impl;

import com.bankdemoproject.entities.Customer;
import com.bankdemoproject.entities.Represantive;
import com.bankdemoproject.enums.REnum;
import com.bankdemoproject.repositories.CustomerRepository;
import com.bankdemoproject.repositories.RepresantiveRepository;
import com.bankdemoproject.services.methodInterface.RepresantiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RepresantiveServiceImpl implements RepresantiveService {
    RepresantiveRepository represantiveRepository;
    CustomerRepository customerRepository;
    @Override
    public ResponseEntity<?> accountAplication(Represantive represantive) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<Represantive> hasRepresantiveTc=represantiveRepository.findByTcNo(represantive.getTcNo());
        try {
            if (hasRepresantiveTc.isPresent()){
                hashMap.put(REnum.status,false);
                hashMap.put(REnum.message,"User already exists");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }else{
                represantive.setPassword(Base64.getEncoder().encodeToString(represantive.getPassword().getBytes()));
                represantiveRepository.save(represantive);
                hashMap.put(REnum.status,true);
                hashMap.put(REnum.result,represantive);
                return new ResponseEntity<>(hashMap,HttpStatus.OK);
            }
        }catch (Exception e){
            hashMap.put(REnum.status,false);
            hashMap.put(REnum.message,e.getMessage());
            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> login(String tcNo, String password) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<Represantive> hasCustomerLogin=represantiveRepository.findByTcNoAndPassword(tcNo,Base64.getEncoder().encodeToString(password.getBytes()));
        if (hasCustomerLogin.isPresent()){
            hashMap.put(REnum.status,true);
            hashMap.put(REnum.result,"LOGIN OK");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(REnum.status,false);
        hashMap.put(REnum.message,"Customer NO or Password Incorrect");
        return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> accountClosing(String tcNo, String password, String phoneNo) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<Represantive> hasCustomerDelete =represantiveRepository.findByTcNoAndPasswordAndPhoneNo(tcNo,password,phoneNo);
        if (hasCustomerDelete.isPresent()){
            represantiveRepository.deleteByTcNo(tcNo);
            hashMap.put(REnum.status,true);
            hashMap.put(REnum.result,"Delete Ok");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(REnum.status,false);
        hashMap.put(REnum.message,"Not delete");
        return new ResponseEntity<>(hashMap,HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<?> getAllCustomer() {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        hashMap.put(REnum.status,true);
        hashMap.put(REnum.result,customerRepository.findAll());
        return new ResponseEntity<>(hashMap,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCustomer(String cus) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
try {
    Optional<Customer> hasCustomerTc=customerRepository.findByTcNo(cus);
    Optional<Customer> hasCustomerYb=customerRepository.findByYbNo(cus);
    Optional<Customer> hasCustomerTax=customerRepository.findByTaxNo(cus);
    if (hasCustomerTc.isPresent()||hasCustomerYb.isPresent()||hasCustomerTax.isPresent()){
        List<Customer> customers=customerRepository.findByTcNoOrYbNoOrTaxNo(cus,cus,cus);
        hashMap.put(REnum.status,true);
        hashMap.put(REnum.result,customers);
        return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
    }else {
        hashMap.put(REnum.status,false);
        hashMap.put(REnum.message,"Not Exists");
        return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
    }
}catch (Exception e){
    hashMap.put(REnum.status,false);
    hashMap.put(REnum.message,e.getMessage());
    return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
}

    }

    @Override
    public ResponseEntity<?> getTopManager(Long repId) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();

      try {
        Represantive optionalRepresantive=represantiveRepository.findByRepIdEqualsAndTopManager(repId);
        if (!(optionalRepresantive ==null)){
       hashMap.put(REnum.status,true);
       hashMap.put(REnum.result,optionalRepresantive);}
        else {
            hashMap.put(REnum.status,false);
            hashMap.put(REnum.result,"Kayıt Bulunamadı");
            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
        }
    }catch (Exception e){
          hashMap.put(REnum.status,false);
          hashMap.put(REnum.result,e.getMessage());
          return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
      }

        return new ResponseEntity<>(hashMap,HttpStatus.OK);
    }
}
