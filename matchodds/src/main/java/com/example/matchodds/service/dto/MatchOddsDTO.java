package com.example.matchodds.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchOddsDTO {
    @NotNull
    public Long matchId;
    @NotBlank
    public String specifier;
    @NotNull
    public Double odd;
}