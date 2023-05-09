package com.bankdemoproject.controllers;

import com.bankdemoproject.entities.Customer;
import com.bankdemoproject.services.methodInterface.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/cus-v1")
public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity accountAplication(@RequestBody Customer customer){
        return customerService.accountAplication(customer);
    }
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestParam String customerNo, @Valid @RequestParam String password){
        return customerService.login(customerNo,password);
    }
    @DeleteMapping("/delete")
    public ResponseEntity accountClosing(@RequestParam String customerNo,@RequestParam String password,@RequestParam String phoneNo){
        return customerService.accountClosing(customerNo,password,phoneNo);
    }

}
