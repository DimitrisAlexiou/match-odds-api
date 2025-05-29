package com.example.matchodds.dto;

import com.example.matchodds.Match;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MatchDTO {
    public Long id;
    @NotBlank public String description;
    @NotNull public String matchDate;
    @NotNull public String matchTime;
    @NotBlank public String teamA;
    @NotBlank public String teamB;
    @NotNull public String sport;

    public static MatchDTO fromEntity(Match match) {
        MatchDTO dto = new MatchDTO();
        dto.id = match.getId();
        dto.description = match.getDescription();
        dto.matchDate = match.getMatchDate() != null ? match.getMatchDate().toString() : "";
        dto.matchTime = match.getMatchTime() != null ? match.getMatchTime().toString() : "";
        dto.teamA = match.getTeamA();
        dto.teamB = match.getTeamB();
        dto.sport = match.getSport() != null ? match.getSport().name() : "";
        return dto;
    }
    
    public Match toEntity() {
        Match match = new Match();
        match.setId(id);
        match.setDescription(description);
        match.setMatchDate(java.time.LocalDate.parse(matchDate));
        match.setMatchTime(java.time.LocalTime.parse(matchTime));
        match.setTeamA(teamA);
        match.setTeamB(teamB);
        match.setSport(Match.Sport.valueOf(sport));
        return match;
    }
} 