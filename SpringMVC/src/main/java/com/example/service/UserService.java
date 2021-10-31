package com.example.service;

import com.example.ecxeption.UserAlreadyExistsException;
import com.example.model.Role;
import com.example.model.User;
import com.example.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            throw new UserAlreadyExistsException("Such user with username: " + user.getUsername() + " already exists!");
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(user.getPassword());
        userRepo.save(user);
    }

    public User getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }
}
