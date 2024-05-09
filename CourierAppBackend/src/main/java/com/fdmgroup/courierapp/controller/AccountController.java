package com.fdmgroup.courierapp.controller;
import com.fdmgroup.courierapp.apimodel.RequestRegister;
import com.fdmgroup.courierapp.apimodel.ResponseRegister;
import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.model.Courier;
import com.fdmgroup.courierapp.model.Customer;
import com.fdmgroup.courierapp.service.AccountService;
import com.fdmgroup.courierapp.service.CourierService;
import com.fdmgroup.courierapp.service.CustomerService;
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
    CustomerService customerService;

    @Autowired
    CourierService courierService;

    @PostMapping("/register")
    public ResponseEntity<ResponseRegister> registerSender(@RequestBody RequestRegister requestRegister) {
        //check duplicate email
        if (customerService.isDuplicateEmail(requestRegister.getEmail())){
            ResponseRegister response = new ResponseRegister("Failed", "Duplicate Email");
            return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
        }
        //Account object creation
        Account newAccount = new Account();
        newAccount.setUsername(requestRegister.getUsername());
        newAccount.setPassword(requestRegister.getPassword());
        newAccount.setAccountType("Sender");
        //Sender object creation
        Customer customer = new Customer();
        customer.setFullName(requestRegister.getFullName());
        customer.setEmail(requestRegister.getEmail());
        customer.setPhoneNo(requestRegister.getPhoneNo());
        //Registering new account
        try {
            newAccount = accountService.registerAccount(newAccount);
        } catch (Exception e) {
            ResponseRegister response = new ResponseRegister("Failed", e.getMessage());
            return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
        }
        customer.setAccountId(newAccount.getAccountId());
        customerService.registerCustomer(customer);
        ResponseRegister response = new ResponseRegister("Success", "Sender Account Registered Successfully");
        return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
    }

    @PostMapping("/registerCourier")
    public ResponseEntity<ResponseRegister> registerCourier(@RequestBody RequestRegister requestRegister) {
        //Account object creation
        Account newAccount = new Account();
        newAccount.setUsername(requestRegister.getUsername());
        newAccount.setPassword(requestRegister.getPassword());
        newAccount.setAccountType("Courier");
        //Courier object creation
        Courier courier = new Courier();
        courier.setFullName(requestRegister.getFullName());
        try {
            courier.setVehicleCapacity(Float.parseFloat(requestRegister.getVehicleCapacity()));
        } catch (Exception e) {
            ResponseRegister response = new ResponseRegister("Failed", "Invalid Vehicle Capacity Input");
            return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
        }
        //Registering new account
        try {
            newAccount = accountService.registerAccount(newAccount);
        } catch (Exception e) {
            ResponseRegister response = new ResponseRegister("Failed", e.getMessage());
            return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
        }
        courier.setAccountId(newAccount.getAccountId());
        courierService.registerCourier(courier);
        ResponseRegister response = new ResponseRegister("Success", "Courier Account Registered Successfully");
        return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
    }
}
