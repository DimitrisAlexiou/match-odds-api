package com.example.matchodds.model;

import com.example.matchodds.enums.Sport;
import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "match_date")
    private LocalDate matchDate;

    @NotNull
    @Column(name = "match_time")
    private String matchTime;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "sport")
    private Sport sport;
}