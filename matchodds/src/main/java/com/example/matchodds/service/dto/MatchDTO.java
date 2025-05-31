package com.example.matchodds.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    @NotBlank
    public String description;
    @NotNull
    public LocalDate matchDate;
    @NotNull
    public String matchTime;
    @NotBlank
    public String teamA;
    @NotBlank
    public String teamB;
    @NotNull
    public Integer sport; // 1 for Football, 2 for Basketball
}