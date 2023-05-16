package com.bankdemoproject.services.methodInterface;

import com.bankdemoproject.entities.Customer;
import com.bankdemoproject.entities.Represantive;
import org.springframework.http.ResponseEntity;

public interface RepresantiveService {
    ResponseEntity<?> accountAplication(Represantive represantive);
    ResponseEntity<?> login(String tcNo,String password);
    ResponseEntity<?> accountClosing(String tcNo,String password,String phoneNo);
    ResponseEntity<?> getAllCustomer();
    ResponseEntity<?> getCustomer(String cus);
    ResponseEntity<?> getTopManager(Long repId);

}
