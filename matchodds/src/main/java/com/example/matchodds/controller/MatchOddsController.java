package com.example.matchodds.controller;

import com.example.matchodds.service.MatchOddsService;
import com.example.matchodds.service.MatchService;
import com.example.matchodds.service.dto.MatchOddsDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/match-odds")
public class MatchOddsController {
    private final com.example.matchodds.service.MatchService matchService;
    private final MatchOddsService matchOddsService;

    @Autowired
    public MatchOddsController(MatchOddsService matchOddsService, MatchService matchService) {
        this.matchOddsService = matchOddsService;
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchOddsDTO>> getAllMatchOdds() {
        return ResponseEntity.ok().body(matchOddsService.getAllMatchOdds());
    }

    @GetMapping("/{matchId}/{specifier}")
    public ResponseEntity<Optional<MatchOddsDTO>> getMatchOddsByMatchIdAndSpecifier(
            @PathVariable Long matchId,
            @PathVariable String specifier) {
        return ResponseEntity.ok().body(matchOddsService.getMatchOddsByMatchIdAndSpecifier(matchId, specifier));
    }

    @PostMapping
    public MatchOddsDTO createMatchOdds(@RequestBody @Valid MatchOddsDTO matchOddsDTO) {
        return ResponseEntity.ok().body(matchOddsService.createMatchOdd(matchOddsDTO)).getBody();
    }

    @PutMapping("/{matchId}/{specifier}")
    public ResponseEntity<MatchOddsDTO> updateMatchOdds(
            @PathVariable Long matchId,
            @PathVariable String specifier,
            @RequestBody @Valid MatchOddsDTO matchOddsDTO) {
        return ResponseEntity.ok(matchOddsService.updateMatchOdds(matchId, specifier, matchOddsDTO));
    }

    @DeleteMapping("/{matchId}/{specifier}")
    public ResponseEntity<Void> deleteMatchOdds(
            @PathVariable Long matchId,
            @PathVariable String specifier) {
        matchOddsService.deleteMatchOdds(matchId, specifier);
        return ResponseEntity.noContent().build();
    }
}