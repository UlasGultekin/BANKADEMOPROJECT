package com.bankdemoproject.controllers;

import com.bankdemoproject.entities.Customer;
import com.bankdemoproject.entities.Represantive;
import com.bankdemoproject.services.methodInterface.RepresantiveService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/rep-v1")
public class RepresantiveController {
    RepresantiveService represantiveService;
    @PostMapping("/create")
    public ResponseEntity accountAplication(@RequestBody Represantive represantive){
        return represantiveService.accountAplication(represantive);
    }
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestParam String tcNo, @Valid @RequestParam String password){
        return represantiveService.login(tcNo,password);
    }
    @DeleteMapping("/delete")
    public ResponseEntity accountClosing(@RequestParam String tcNo,@RequestParam String password,@RequestParam String phoneNo){
        return represantiveService.accountClosing(tcNo,password,phoneNo);
    }
    @GetMapping("/get-customerByRepresantive")
    public ResponseEntity getAllCustomer(){
        return represantiveService.getAllCustomer();
    }

}
