package com.example.matchodds.controller;

import com.example.matchodds.Match;
import com.example.matchodds.service.MatchService;
import com.example.matchodds.dto.MatchDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<MatchDTO> getAllMatches() {
        return matchService.findAll().stream().map(MatchDTO::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MatchDTO getMatchById(@PathVariable Long id) {
        return matchService.findById(id)
                .map(MatchDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + id));
    }

    @PostMapping
    public MatchDTO createMatch(@RequestBody @Valid MatchDTO matchDTO) {
        return matchService.findByDescriptionAndMatchDate(matchDTO.description, matchDTO.matchDate)
                .map(existing -> {
                    throw new DuplicateResourceException("Match already exists with description '" + matchDTO.description + "' and date '" + matchDTO.matchDate + "'");
                })
                .orElseGet(() -> {
                    Match match = matchDTO.toEntity();
                    return MatchDTO.fromEntity(matchService.save(match));
                });
    }

    @PutMapping("/{id}")
    public MatchDTO updateMatch(@PathVariable Long id, @RequestBody @Valid MatchDTO matchDTO) {
        return matchService.findById(id)
                .map(existing -> {
                    Match updated = matchDTO.toEntity();
                    updated.setId(id);
                    return MatchDTO.fromEntity(matchService.save(updated));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        return matchService.findById(id)
                .map(existing -> {
                    matchService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + id));
    }
} 