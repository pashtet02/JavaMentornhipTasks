package com.example.service;

import com.example.model.UserAccount;
import com.example.repos.AccountRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService service;

    @Mock private AccountRepo mockAccountRepo;

    @Test
    void createUserAccount() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUser(null);
        userAccount.setMoney(5.0);
        userAccount.setId(15L);

        when(mockAccountRepo.save(any(UserAccount.class))).thenReturn(userAccount);

        UserAccount actualAccount = service.createUserAccount(5.0);
        assertNull(actualAccount.getUser());
        assertEquals(userAccount.getMoney(), actualAccount.getMoney(), 0.0);
        assertEquals(15L, actualAccount.getId());
    }
}