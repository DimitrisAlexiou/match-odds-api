package com.example.matchodds.controller;

import com.example.matchodds.service.MatchService;
import com.example.matchodds.service.dto.MatchDTO;
import com.example.matchodds.service.dto.MatchPartialDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        return ResponseEntity.ok().body(matchService.getAllMatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok().body(matchService.getMatchById(id));
    }

    @PostMapping
    public MatchDTO createMatch(@RequestBody @Valid MatchDTO matchDTO) {
        return ResponseEntity.ok().body(matchService.createMatch(matchDTO)).getBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable Long id, @RequestBody @Valid MatchDTO matchDTO) {
        return ResponseEntity.ok().body(matchService.updateMatch(id, matchDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MatchDTO> partialUpdateMatch(@PathVariable Long id,
                                                       @RequestBody MatchPartialDTO partialDTO) {
        return ResponseEntity.ok().body(matchService.partialUpdateMatch(id, partialDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}