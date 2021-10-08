package com.learnkafka.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryEvent {
    @Id
    @GeneratedValue
    private Integer libraryId;

    @Enumerated(EnumType.STRING)
    private LibraryEventType libraryEventType;

    @OneToOne(mappedBy = "libraryEvent" ,cascade = CascadeType.ALL)
    @ToString.Exclude
    private Book book;

}
