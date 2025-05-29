package com.example.matchodds.controller;

import com.example.matchodds.MatchOdds;
import com.example.matchodds.service.MatchOddsService;
import com.example.matchodds.service.MatchService;
import com.example.matchodds.dto.MatchOddsDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/match-odds")
public class MatchOddsController {
    private final MatchOddsService matchOddsService;
    private final MatchService matchService;

    public MatchOddsController(MatchOddsService matchOddsService, MatchService matchService) {
        this.matchOddsService = matchOddsService;
        this.matchService = matchService;
    }

    @GetMapping
    public List<MatchOddsDTO> getAllMatchOdds() {
        return matchOddsService.findAll().stream().map(MatchOddsDTO::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MatchOddsDTO getMatchOddsById(@PathVariable Long id) {
        return matchOddsService.findById(id)
                .map(MatchOddsDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("MatchOdds not found with id " + id));
    }

    @PostMapping
    public MatchOddsDTO createMatchOdds(@RequestBody @Valid MatchOddsDTO dto) {
        return matchService.findById(dto.matchId)
                .map(match -> {
                    MatchOdds matchOdds = dto.toEntity();
                    matchOdds.setMatch(match);
                    return MatchOddsDTO.fromEntity(matchOddsService.save(matchOdds));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + dto.matchId));
    }

    @PutMapping("/{id}")
    public MatchOddsDTO updateMatchOdds(@PathVariable Long id, @RequestBody @Valid MatchOddsDTO dto) {
        return matchOddsService.findById(id)
                .flatMap(existing -> matchService.findById(dto.matchId)
                        .map(match -> {
                            existing.setMatch(match);
                            existing.setSpecifier(dto.specifier);
                            existing.setOdd(dto.odd);
                            return MatchOddsDTO.fromEntity(matchOddsService.save(existing));
                        })
                )
                .orElseThrow(() -> new ResourceNotFoundException("MatchOdds or Match not found with id " + id + " / " + dto.matchId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchOdds(@PathVariable Long id) {
        return matchOddsService.findById(id)
                .map(existing -> {
                    matchOddsService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("MatchOdds not found with id " + id));
    }
} 