package com.cpd.springcore.service;

import com.cpd.springcore.model.User;
import com.cpd.springcore.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User getUserById(long userId) {
        return userRepo.findById(userId).orElseThrow();
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userRepo.getUsersByName(name,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "name")));
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public boolean delete(long userId) {
        userRepo.deleteById(userId);
        return true;
    }
}
