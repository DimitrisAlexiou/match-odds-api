package com.example.matchodds.service.impl;

import com.example.matchodds.enums.Sport;
import com.example.matchodds.exceptions.DuplicateResourceException;
import com.example.matchodds.exceptions.ResourceNotFoundException;
import com.example.matchodds.model.Match;
import com.example.matchodds.repository.MatchRepository;
import com.example.matchodds.service.MatchService;
import com.example.matchodds.service.dto.MatchDTO;
import com.example.matchodds.service.dto.MatchPartialDTO;
import com.example.matchodds.service.mappers.MatchMapper;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
public class MatchServiceImpl implements MatchService {
    private final MatchMapper matchMapper;
    private final MatchRepository matchRepository;

    public MatchServiceImpl(MatchMapper matchMapper, MatchRepository matchRepository) {
        this.matchMapper = matchMapper;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<MatchDTO> getAllMatches() {
        log.info("Fetching all matches");
        return matchRepository.findAll().stream().map(matchMapper::toDTO).toList();
    }

    @Override
    public MatchDTO getMatchById(Long id) {
        log.info("Fetching match with id: {}", id);
        Optional<Match> optionalMatch = matchRepository.findById(id);
        return matchMapper.toDTO(optionalMatch
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + id)));
    }

    @Override
    public MatchDTO createMatch(MatchDTO matchDTO) {
        log.info("Creating match with description: {} and date: {}", matchDTO.getDescription(), matchDTO.getMatchDate());
        Optional<Match> existingMatch = matchRepository.findByDescriptionAndMatchDate(matchDTO.getDescription(), matchDTO.getMatchDate());
        if (existingMatch.isPresent()) {
            log.error("Match already exists with description: {} and date: {}", matchDTO.getDescription(), matchDTO.getMatchDate());
            throw new DuplicateResourceException("Match already exists with description '" + matchDTO.getDescription() + "' and date '" + matchDTO.getMatchDate() + "'");
        }
        Match match = matchRepository.save(matchMapper.toEntity(matchDTO));
        return matchMapper.toDTO(match);
    }

    @Override
    public MatchDTO updateMatch(Long id, MatchDTO matchDTO) {
        log.info("Updating match with id: {}", id);

        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + id));

        matchMapper.updateEntityFromDTO(matchDTO, existingMatch);

        Match updatedMatch = matchRepository.save(existingMatch);
        return matchMapper.toDTO(updatedMatch);
    }

    @Override
    public MatchDTO partialUpdateMatch(Long id, MatchPartialDTO partialDTO) {
        log.info("Partially updating match with id: {}", id);

        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + id));

        if (partialDTO.getDescription() != null) {
            existingMatch.setDescription(partialDTO.getDescription());
        }
        if (partialDTO.getMatchDate() != null) {
            existingMatch.setMatchDate(partialDTO.getMatchDate());
        }
        if (partialDTO.getMatchTime() != null) {
            existingMatch.setMatchTime(partialDTO.getMatchTime());
        }
        if (partialDTO.getTeamA() != null) {
            existingMatch.setTeamA(partialDTO.getTeamA());
        }
        if (partialDTO.getTeamB() != null) {
            existingMatch.setTeamB(partialDTO.getTeamB());
        }
        if (partialDTO.getSport() != null) {
            existingMatch.setSport(partialDTO.getSport() == 1 ? Sport.FOOTBALL : Sport.BASKETBALL);
        }

        Match updatedMatch = matchRepository.save(existingMatch);

        return matchMapper.toDTO(updatedMatch);
    }

    @Override
    public void deleteMatch(Long id) {
        log.info("Deleting match with id: {}", id);
        if (!matchRepository.existsById(id)) {
            log.error("Match not found with id: {}", id);
            throw new ResourceNotFoundException("Match not found with id " + id);
        }
        matchRepository.deleteById(id);
    }
}
