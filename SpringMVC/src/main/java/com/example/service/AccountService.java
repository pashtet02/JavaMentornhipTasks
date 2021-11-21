package com.example.service;

import com.example.model.User;
import com.example.model.UserAccount;
import com.example.repos.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;

    public UserAccount createUserAccount(double deposit){
        UserAccount account = new UserAccount();
        account.setMoney(deposit);
        return accountRepo.save(account);
    }

    public UserAccount updateAccount(UserAccount account) {
        return accountRepo.save(account);
    }
}
