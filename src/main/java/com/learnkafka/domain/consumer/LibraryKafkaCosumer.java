package com.learnkafka.domain.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.domain.service.LibraryEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryKafkaCosumer {
    @Autowired
    private LibraryEventService libraryEventService;

    @KafkaListener(topics ={"library-events"},groupId = "library-book")
    public  void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        System.out.println("on Message" +consumerRecord);
        libraryEventService.processLibraryevent(consumerRecord);

    }
}
