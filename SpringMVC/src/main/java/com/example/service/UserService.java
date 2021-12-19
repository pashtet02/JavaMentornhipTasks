package com.example.service;

import com.example.aspect.Loggable;
import com.example.ecxeption.UserAlreadyExistsException;
import com.example.model.UserAccount;
import com.example.model.enums.Role;
import com.example.model.User;
import com.example.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final AccountService accountService;

    @Loggable
    public User addUser(User user, double deposit) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            throw new UserAlreadyExistsException("Such user with username: " + user.getUsername() + " already exists!");
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(user.getPassword());

        UserAccount userAccount = accountService.createUserAccount(deposit);
        user.setAccount(userAccount);

        return userRepo.save(user);
    }

    @Loggable
    public User getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long userId){
        return userRepo.findById(userId);
    }

    @Cacheable("users")
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
