package com.example.matchodds.service.impl;

import com.example.matchodds.exceptions.DuplicateResourceException;
import com.example.matchodds.exceptions.ResourceNotFoundException;
import com.example.matchodds.model.Match;
import com.example.matchodds.model.MatchOdds;
import com.example.matchodds.repository.MatchOddsRepository;
import com.example.matchodds.repository.MatchRepository;
import com.example.matchodds.service.MatchOddsService;
import com.example.matchodds.service.dto.MatchOddsDTO;
import com.example.matchodds.service.mappers.MatchOddsMapper;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
public class MatchOddsServiceImpl implements MatchOddsService {
    private final MatchOddsMapper matchOddsMapper;
    private final MatchOddsRepository matchOddsRepository;
    private final MatchRepository matchRepository;

    public MatchOddsServiceImpl(MatchOddsMapper matchOddsMapper, MatchOddsRepository matchOddsRepository, MatchRepository matchRepository) {
        this.matchOddsMapper = matchOddsMapper;
        this.matchOddsRepository = matchOddsRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<MatchOddsDTO> getAllMatchOdds() {
        log.info("Fetching all match odds");
        return matchOddsRepository.findAll().stream().map(matchOddsMapper::toDTO).toList();
    }

    @Override
    public Optional<MatchOddsDTO> getMatchOddsByMatchIdAndSpecifier(Long matchId, String specifier) {
        log.info("Fetching match odd for matchId: {} and specifier: {}", matchId, specifier);
        return Optional.ofNullable(matchOddsMapper.toDTO(
                matchOddsRepository.findByMatchIdAndSpecifier(matchId, specifier)
                        .orElseThrow(() -> new ResourceNotFoundException("Match odd not found for this match."))));
    }

    @Override
    public MatchOddsDTO createMatchOdd(MatchOddsDTO matchOddsDTO) {
        log.info("Creating match odd with specifier: {} and odd: {}", matchOddsDTO.getSpecifier(), matchOddsDTO.getOdd());

        Match match = matchRepository.findById(matchOddsDTO.getMatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + matchOddsDTO.getMatchId()));

        matchOddsRepository.findByMatchIdAndSpecifier(matchOddsDTO.getMatchId(), matchOddsDTO.getSpecifier())
                .ifPresent(existing -> {
                    log.error("Match odd already exists for matchId: {}, specifier: {}", matchOddsDTO.getMatchId(), matchOddsDTO.getSpecifier());
                    throw new DuplicateResourceException("Match odd already exists for matchId '"
                            + matchOddsDTO.getMatchId() + "' and specifier '" + matchOddsDTO.getSpecifier() + "'");
                });

        MatchOdds matchOdds = matchOddsMapper.toEntity(matchOddsDTO);
        matchOdds.setMatch(match);

        return matchOddsMapper.toDTO(matchOddsRepository.save(matchOdds));
    }

    @Override
    public MatchOddsDTO updateMatchOdds(Long matchId, String specifier, MatchOddsDTO matchOddsDTO) {
        MatchOdds existing = matchOddsRepository.findByMatchIdAndSpecifier(matchId, specifier)
                .orElseThrow(() -> new ResourceNotFoundException("Match odd not found for this match."));

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found."));

        matchOddsMapper.updateEntityFromDTO(matchOddsDTO, existing);
        existing.setMatch(match);

        return matchOddsMapper.toDTO(matchOddsRepository.save(existing));
    }

    @Override
    public void deleteMatchOdds(Long matchId, String specifier) {
        log.info("Deleting match odd with for match with id: {}", matchId);
        MatchOdds existing = matchOddsRepository.findByMatchIdAndSpecifier(matchId, specifier)
                .orElseThrow(() -> new ResourceNotFoundException("Match odd not found for this match."));
        matchOddsRepository.delete(existing);
    }
}
