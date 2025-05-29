package com.example.matchodds;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByDescriptionAndMatchDate(String description, LocalDate matchDate);
} 