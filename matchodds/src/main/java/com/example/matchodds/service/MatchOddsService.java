package com.example.matchodds.service;

import com.example.matchodds.MatchOdds;
import com.example.matchodds.MatchOddsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchOddsService {
    private final MatchOddsRepository matchOddsRepository;

    public MatchOddsService(MatchOddsRepository matchOddsRepository) {
        this.matchOddsRepository = matchOddsRepository;
    }

    public List<MatchOdds> findAll() {
        return matchOddsRepository.findAll();
    }

    public Optional<MatchOdds> findById(Long id) {
        return matchOddsRepository.findById(id);
    }

    public MatchOdds save(MatchOdds matchOdds) {
        return matchOddsRepository.save(matchOdds);
    }

    public void deleteById(Long id) {
        matchOddsRepository.deleteById(id);
    }
} 