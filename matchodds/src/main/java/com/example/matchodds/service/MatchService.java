package com.example.matchodds.service;

import com.example.matchodds.service.dto.MatchDTO;
import com.example.matchodds.service.dto.MatchPartialDTO;

import java.util.List;

/**
 * Service interface for managing matches.
 * Provides methods to create, retrieve, update, and delete matches.
 */
public interface MatchService {
    /**
     * Retrieves all the created matches.
     *
     * @return a list of all the created matches
     */
    List<MatchDTO> getAllMatches();

    /**
     * Creates a new match with the given details.
     *
     * @param matchDTO the details of the match to create
     * @return the created match
     */
    MatchDTO createMatch(MatchDTO matchDTO);

    /**
     * Retrieves a match by its ID.
     *
     * @param id the ID of the match to retrieve
     * @return the match with the specified ID
     */
    MatchDTO getMatchById(Long id);

    /**
     * Updates an existing match with the given details.
     *
     * @param id       the ID of the match to update
     * @param matchDTO the new details for the match
     * @return the updated match
     */
    MatchDTO updateMatch(Long id, MatchDTO matchDTO);

    /**
     * Partially updates a match with the given details.
     *
     * @param id          the ID of the match to update
     * @param partialDTO  the partial details for the match
     * @return the updated match
     */
    MatchDTO partialUpdateMatch(Long id, MatchPartialDTO partialDTO);

    /**
     * Deletes a match by its ID.
     *
     * @param id the ID of the match to delete
     */
    void deleteMatch(Long id);
}