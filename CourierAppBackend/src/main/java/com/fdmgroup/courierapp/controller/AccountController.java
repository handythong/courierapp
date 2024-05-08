package com.fdmgroup.courierapp.controller;
import com.fdmgroup.courierapp.apimodel.RequestRegister;
import com.fdmgroup.courierapp.apimodel.ResponseRegister;
import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.model.Sender;
import com.fdmgroup.courierapp.service.AccountService;
import com.fdmgroup.courierapp.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    SenderService senderService;

    @PostMapping("/register")
    public ResponseEntity<ResponseRegister> registerUser(@RequestBody RequestRegister requestRegister) {
        //check duplicate email
        if (senderService.isDuplicateEmail(requestRegister.getEmail())){
            ResponseRegister response = new ResponseRegister("Failed", "Duplicate Email");
            return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
        }
        //Account object creation
        Account newAccount = new Account();
        newAccount.setUsername(requestRegister.getUsername());
        newAccount.setPassword(requestRegister.getPassword());
        newAccount.setAccountType("Sender");
        //Registering new account
        try {
            newAccount = accountService.registerAccount(newAccount);
        } catch (Exception e) {
            ResponseRegister response = new ResponseRegister("Failed", e.getMessage());
            return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
        }
        //Sender object creation
        Sender sender = new Sender();
        sender.setFullName(requestRegister.getFullName());
        sender.setEmail(requestRegister.getEmail());
        sender.setPhoneNo(requestRegister.getPhoneNo());
        sender.setAccountId(newAccount.getAccountId());
        senderService.registerSender(sender);
        ResponseRegister response = new ResponseRegister("Success", "Account Registered Successfully");
        return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
    }
}
