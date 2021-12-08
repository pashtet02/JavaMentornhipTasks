package com.impl.repos;
import com.impl.model.EventImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends CrudRepository<EventImpl, Long> {
    List<EventImpl> findAllByTitle(String title);
}

