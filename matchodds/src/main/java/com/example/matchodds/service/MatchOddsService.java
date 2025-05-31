package com.example.matchodds.service;

import com.example.matchodds.service.dto.MatchOddsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing match odds.
 * Provides methods to retrieve, save, and delete match odds.
 */
public interface MatchOddsService {
    /**
     * Retrieves all match odds.
     *
     * @return a list of all match odds
     */
    List<MatchOddsDTO> getAllMatchOdds();

    /**
     * Retrieves match odds by ID.
     *
     * @param matchId the ID of the match to retrieve odds for
     * @param specifier the specifier of the match odds to retrieve
     * @return an Optional containing the match odds if found, or empty if not found
     */
    Optional<MatchOddsDTO> getMatchOddsByMatchIdAndSpecifier(Long matchId, String specifier);

    /**
     * Saves the given match odds.
     *
     * @param matchOdds the match odds to save
     * @return the saved match odds
     */
    MatchOddsDTO createMatchOdd(MatchOddsDTO matchOdds);

    /**
     * Updates the match odds with the given ID.
     *
     * @param matchId    the ID of the match to update its odds for
     * @param specifier  the specifier of the match odds to update
     * @param matchOdds  the new match odds details
     * @return the updated match odds
     */
    MatchOddsDTO updateMatchOdds(Long matchId, String specifier, MatchOddsDTO matchOdds);
    /**
     * Deletes match odds by match ID.
     *
     * @param matchId   the ID of the match to delete its odds for
     * @param specifier the specifier of the match odds to delete
     */
    void deleteMatchOdds(Long matchId, String specifier);
}