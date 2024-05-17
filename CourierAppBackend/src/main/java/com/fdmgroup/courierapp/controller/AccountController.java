package com.fdmgroup.courierapp.controller;
import com.fdmgroup.courierapp.apimodel.RequestLogin;
import com.fdmgroup.courierapp.apimodel.RequestRegister;
import com.fdmgroup.courierapp.apimodel.ResponseLogin;
import com.fdmgroup.courierapp.apimodel.ResponseRegister;
import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.model.Courier;
import com.fdmgroup.courierapp.model.Customer;
import com.fdmgroup.courierapp.security.JwtProvider;
import com.fdmgroup.courierapp.service.AccountService;
import com.fdmgroup.courierapp.service.CourierService;
import com.fdmgroup.courierapp.service.CustomerService;
import com.fdmgroup.courierapp.service.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private UserDetailsServiceImplementation customUserDetails;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        newAccount.setRole("ROLE_CUSTOMER");
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
        customer.setAccountId(newAccount.getId());
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
        newAccount.setRole("ROLE_COURIER");
        //Courier object creation
        Courier courier = new Courier();
        courier.setFullName(requestRegister.getFullName());
        float vehicleCapacity;
        try {
            vehicleCapacity = Float.parseFloat(requestRegister.getVehicleCapacity());
            if (vehicleCapacity <= 0) {
                ResponseRegister response = new ResponseRegister("Failed", "Invalid Vehicle Capacity Input");
                return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
            }
            courier.setVehicleCapacity(vehicleCapacity);
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
        courier.setAccountId(newAccount.getId());
        courierService.registerCourier(courier);
        ResponseRegister response = new ResponseRegister("Success", "Courier Account Registered Successfully");
        return new ResponseEntity<ResponseRegister>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login(@RequestBody RequestLogin requestLogin) {
        String username = requestLogin.getUsername();
        String password = requestLogin.getPassword();
        try {
            Authentication authentication = authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = JwtProvider.generateToken(authentication);
            String role = authentication.getAuthorities().toString();
            ResponseLogin responseLogin = new ResponseLogin("Success","Login Success", role, token);
            return new ResponseEntity<>(responseLogin, HttpStatus.OK);
        } catch (Exception e) {
            ResponseLogin responseLogin = new ResponseLogin("Failed", e.getMessage());
            return new ResponseEntity<>(responseLogin, HttpStatus.OK);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                null, null, null
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().build();
    }

    private Authentication authenticate(String username, String password) throws BadCredentialsException, UsernameNotFoundException {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        } else {
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
    }
}
