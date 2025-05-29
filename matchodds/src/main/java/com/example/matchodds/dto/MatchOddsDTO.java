package com.example.matchodds.dto;

import com.example.matchodds.MatchOdds;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MatchOddsDTO {
    public Long id;
    @NotNull public Long matchId;
    @NotBlank public String specifier;
    @NotNull public Double odd;

    public static MatchOddsDTO fromEntity(MatchOdds mo) {
        MatchOddsDTO dto = new MatchOddsDTO();
        dto.id = mo.getId();
        dto.matchId = mo.getMatch() != null ? mo.getMatch().getId() : "";
        dto.specifier = mo.getSpecifier();
        dto.odd = mo.getOdd();
        return dto;
    }
    
    public MatchOdds toEntity() {
        MatchOdds mo = new MatchOdds();
        mo.setId(id);
        mo.setSpecifier(specifier);
        mo.setOdd(odd);
        return mo;
    }
} 