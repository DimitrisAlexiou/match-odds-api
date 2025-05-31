package com.example.matchodds.service.mappers;

import com.example.matchodds.model.Match;
import com.example.matchodds.service.dto.MatchDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {
    private final ModelMapper modelMapper;

    public MatchMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MatchDTO toDTO(Match match) {
        return modelMapper.map(match, MatchDTO.class);
    }

    public Match toEntity(MatchDTO matchDTO) {
        return modelMapper.map(matchDTO, Match.class);
    }

    public void updateEntityFromDTO(MatchDTO dto, Match entity) {
        modelMapper.map(dto, entity);
    }
}

