package com.example.matchodds;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate matchDate;

    @NotNull
    private LocalTime matchTime;

    @NotBlank
    private String teamA;

    @NotBlank
    private String teamB;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Sport sport;

    public enum Sport {
        FOOTBALL, // 0
        BASKETBALL // 1
    }
} 