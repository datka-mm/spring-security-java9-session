package com.example.springsecurityjava9session.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(generator = "board_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "board_gen", sequenceName = "board_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String imageLink;
    private ZonedDateTime createdAt;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "board")
    private List<Column> columns;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private User creator;
}
