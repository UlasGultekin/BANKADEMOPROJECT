package com.bankdemoproject.services.methodInterface;

import com.bankdemoproject.entities.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface CustomerService {

    ResponseEntity<?> accountAplication(Customer customer);
    ResponseEntity<?> login(String customerNo,String password);
    ResponseEntity<?> accountClosing(String customerNo,String password,String phoneNo);
}
