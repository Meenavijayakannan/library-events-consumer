package com.learnkafka.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    @OneToOne
    @JoinColumn(name="libraryEventId")
    private LibraryEvent libraryEvent;
}
