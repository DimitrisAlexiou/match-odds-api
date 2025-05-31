package com.example.matchodds.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match_odds", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"match_id", "specifier"})
})
public class MatchOdds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "match_id")
    @NotNull
    private Match match;

    @Column(name = "specifier")
    private String specifier;

    @NotNull
    @Column(name = "odd")
    private Double odd;
} 