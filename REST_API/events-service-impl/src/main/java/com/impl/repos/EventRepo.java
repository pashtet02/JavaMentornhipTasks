package com.impl.repos;
import com.impl.model.EventImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface EventRepo extends CrudRepository<EventImpl, Long> {
    List<EventImpl> findAllByTitle(String title);
}

