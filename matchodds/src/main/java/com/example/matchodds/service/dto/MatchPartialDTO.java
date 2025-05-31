package com.example.matchodds.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchPartialDTO {
    private String description;
    private LocalDate matchDate;
    private String matchTime;
    private String teamA;
    private String teamB;
    private Integer sport; // 1 for Football, 2 for Basketball
}