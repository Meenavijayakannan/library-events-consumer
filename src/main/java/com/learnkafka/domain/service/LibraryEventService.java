package com.learnkafka.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.entity.LibraryEvent;
import com.learnkafka.domain.entity.LibraryEventType;
import com.learnkafka.domain.repository.LibraryEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LibraryEventService {

    @Autowired
    ObjectMapper objectMapper ;

    @Autowired
    private LibraryEventRepository libraryEventRepository;

    public void processLibraryevent(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
        LibraryEventType eventType=libraryEvent.getLibraryEventType();
        switch(eventType){
            case NEW:
                save(libraryEvent);
            case UPDATE:
                validate(libraryEvent.getLibraryId());
                save(libraryEvent);
                log.info("Successfully updated");
            default:
                log.info("Invalid Library Event Type");
        }
    }

    private void validate(Integer libraryId) {
        if(libraryId == null){
            throw new IllegalArgumentException("library id is null");
        }
        Optional<LibraryEvent> libraryIdOptional = libraryEventRepository.findById(libraryId);
        if(!libraryIdOptional.isPresent()){
            throw new IllegalArgumentException("Not a valid id");
        }
        log.info("library id from db",libraryIdOptional.get());
    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        libraryEventRepository.save(libraryEvent);
        log.info("Message saved Succeefuly " + libraryEvent);
    }
}
