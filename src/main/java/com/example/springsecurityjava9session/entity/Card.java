package com.example.springsecurityjava9session.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(generator = "card_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "card_gen", sequenceName = "card_seq", allocationSize = 1)
    private Long id;
    private String name;
    private ZonedDateTime deadline;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private Column column;

}
