package com.cpd.springcore.repos;

import com.cpd.springcore.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> getUsersByName(String name, Pageable title);
}
