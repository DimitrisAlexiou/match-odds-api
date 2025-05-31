package com.example.matchodds.service.mappers;

import com.example.matchodds.model.MatchOdds;
import com.example.matchodds.service.dto.MatchOddsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MatchOddsMapper {
    private final ModelMapper modelMapper;

    public MatchOddsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MatchOddsDTO toDTO(MatchOdds matchOdds) {
        MatchOddsDTO dto = modelMapper.map(matchOdds, MatchOddsDTO.class);

        if (matchOdds.getMatch() != null) {
            dto.setMatchId(matchOdds.getMatch().getId());
        }

        return dto;
    }

    public MatchOdds toEntity(MatchOddsDTO matchOddsDTO) {
        return modelMapper.map(matchOddsDTO, MatchOdds.class);
    }

    public void updateEntityFromDTO(MatchOddsDTO dto, MatchOdds entity) {
        modelMapper.map(dto, entity);
    }
}
