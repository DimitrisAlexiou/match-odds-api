package com.example.matchodds.repository;

import com.example.matchodds.model.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {
    Optional<MatchOdds> findByMatchIdAndSpecifier(Long matchId, String specifier);
} 