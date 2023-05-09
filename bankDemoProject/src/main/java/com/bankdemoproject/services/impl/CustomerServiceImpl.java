package com.bankdemoproject.services.impl;

import com.bankdemoproject.entities.Customer;
import com.bankdemoproject.enums.REnum;
import com.bankdemoproject.repositories.CustomerRepository;
import com.bankdemoproject.services.methodInterface.CustomerService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<?> accountAplication(Customer customer) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<Customer> hasCustomerTc=customerRepository.findByTcNo(customer.getTcNo());
        Optional<Customer> hasCustomerYb=customerRepository.findByYbNo(customer.getYbNo());
        Optional<Customer> hasCustomerTax=customerRepository.findByTaxNo(customer.getTaxNo());
        try {


        if (hasCustomerTc.isPresent()||hasCustomerYb.isPresent()||hasCustomerTax.isPresent()){

            hashMap.put(REnum.status,false);
            hashMap.put(REnum.message,"User already exists");
            return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
        }else{
            customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
            customerRepository.save(customer);
            hashMap.put(REnum.status,true);
            hashMap.put(REnum.result,customer);
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
    }catch (Exception e){
            hashMap.put(REnum.status,false);
            hashMap.put(REnum.message,e.getMessage());
            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<?> login(String customerNo, String password) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<Customer> hasCustomerLogin=customerRepository.findByCustomerNoAndPassword(customerNo,Base64.getEncoder().encodeToString(password.getBytes()));
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
    public ResponseEntity<?> accountClosing(String customerNo, String password, String phoneNo) {
        Map<REnum,Object> hashMap=new LinkedHashMap<>();
        Optional<Customer> hasCustomerDelete =customerRepository.findByCustomerNoAndPasswordAndPhoneNo(customerNo,password,phoneNo);
        if (hasCustomerDelete.isPresent()){
            customerRepository.deleteByCustomerNo(customerNo);
            hashMap.put(REnum.status,true);
            hashMap.put(REnum.result,"Delete Ok");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(REnum.status,false);
        hashMap.put(REnum.message,"Not delete");
        return new ResponseEntity<>(hashMap,HttpStatus.NOT_ACCEPTABLE);
    }
}
