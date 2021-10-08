package com.learnkafka.domain.repository;

import com.learnkafka.domain.entity.LibraryEvent;
import org.springframework.data.repository.CrudRepository;

public interface LibraryEventRepository extends CrudRepository<LibraryEvent,Integer> {

}
