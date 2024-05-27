package com.fdmgroup.courierapp.service;


import com.fdmgroup.courierapp.exception.PasswordRuleException;
import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.repository.AccountRepo;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    private final static String USERNAME = "johndoe";
    private final static String NEW_USERNAME = "janedoe";
    private final static String VALID_PASSWORD = "Password12345";
    private final static String ENCRYPTED_VALID_PASSWORD = "encryptedPassword";
    private final static String INVALID_PASSWORD = "password";
    private final static String PASSWORD_RULE_EXCEPTION_MESSAGE = "Password need to contains at least 8 characters and have at least 1 uppercase, 1 lowercase and 1 number";
    private final static String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    private final static String ROLE_COURIER = "ROLE_COURIER";
    private final static Date LAST_UPDATED = new Date();
    private final static Date CREATED_ON = new Date();

    @Mock
    private AccountRepo accountRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountService accountService;

    private Account validAccount;

    @BeforeEach
    void setUp() {
        this.validAccount = new Account();
        validAccount.setId(1L);
        validAccount.setUsername(USERNAME);
        validAccount.setPassword(VALID_PASSWORD);
        validAccount.setRole(ROLE_CUSTOMER);
        validAccount.setLastUpdated(LAST_UPDATED);
        validAccount.setCreatedOn(CREATED_ON);
    }

    @AfterEach
    void tearDown() {
        this.validAccount = null;
    }

    @Test
    public void testRegisterAccount_WithValidPassword() throws Exception {
        when(passwordEncoder.encode(validAccount.getPassword())).thenReturn(ENCRYPTED_VALID_PASSWORD);
        when(accountRepo.save(any(Account.class))).thenReturn(validAccount);

        Account registeredAccount = accountService.registerAccount(validAccount);

        assertNotNull(registeredAccount);
        assertEquals(ENCRYPTED_VALID_PASSWORD, registeredAccount.getPassword());
    }

    @Test
    public void testRegisterAccount_WithInvalidPassword() {
        validAccount.setPassword(INVALID_PASSWORD);

        PasswordRuleException exception = assertThrows(PasswordRuleException.class, () -> {
            accountService.registerAccount(validAccount);
        });

        assertEquals(PASSWORD_RULE_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void testIsDuplicateUsername_WithExistingUsername() {
        when(accountRepo.findByUsername(USERNAME)).thenReturn(Optional.of(validAccount));

        Boolean isDuplicate = accountService.isDuplicateUsername(USERNAME);

        assertTrue(isDuplicate);
    }

    @Test
    public void testIsDuplicateUsername_WithNonExistingUsername() {
        when(accountRepo.findByUsername(NEW_USERNAME)).thenReturn(Optional.empty());

        Boolean isDuplicate = accountService.isDuplicateUsername(NEW_USERNAME);

        assertFalse(isDuplicate);
    }
}
