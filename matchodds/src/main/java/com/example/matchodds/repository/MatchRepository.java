package com.example.matchodds.repository;

import com.example.matchodds.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByDescriptionAndMatchDate(String description, LocalDate matchDate);
} 