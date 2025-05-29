package com.example.matchodds.service;

import com.example.matchodds.Match;
import com.example.matchodds.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public Optional<Match> findById(Long id) {
        return matchRepository.findById(id);
    }

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public void deleteById(Long id) {
        matchRepository.deleteById(id);
    }

    public Optional<Match> findByDescriptionAndMatchDate(String description, String matchDate) {
        return matchRepository.findByDescriptionAndMatchDate(description, LocalDate.parse(matchDate));
    }
} 