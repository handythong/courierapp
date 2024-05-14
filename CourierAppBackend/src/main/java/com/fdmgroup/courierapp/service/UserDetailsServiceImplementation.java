package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.repository.AccountRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    AccountRepo accountRepo;

    private final Logger logger = LogManager.getLogger();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optAccount = accountRepo.findByUsername(username);
        if (optAccount.isEmpty()) {
            throw new UsernameNotFoundException("User not found with this username");
        }
        Account account = optAccount.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole()));
        return new User(
                account.getUsername(),
                account.getPassword(),
                authorities);
    }


}
