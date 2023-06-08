package com.example.springsecurityjava9session.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "columns")
public class Column {

    @Id
    @GeneratedValue(generator = "column_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "column_gen", sequenceName = "column_seq", allocationSize = 1)
    private Long id;
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "column")
    private List<Card> cards;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Board board;

}
