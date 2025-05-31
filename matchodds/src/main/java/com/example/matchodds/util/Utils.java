package com.example.matchodds.util;

import com.example.matchodds.enums.Sport;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.Condition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {
    /**
     * Initializes a ModelMapper instance with strict matching strategy.
     * This method sets up converters for mapping between Integer and Sport enum.
     *
     * @return a configured ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Skip null values during mapping (for partial update)
        Condition<?, ?> notNull = context -> context.getSource() != null;

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(notNull);

        // Integer → Sport (DTO to Entity)
        Converter<Integer, Sport> toSportEnum = ctx -> {
            Integer source = ctx.getSource();
            return source == null ? null : Sport.fromCode(source);
        };

        // Sport → Integer (Entity to DTO)
        Converter<Sport, Integer> fromSportEnum = ctx -> {
            Sport source = ctx.getSource();
            return source == null ? null : source.getCode();
        };

        modelMapper.createTypeMap(Integer.class, Sport.class).setConverter(toSportEnum);
        modelMapper.createTypeMap(Sport.class, Integer.class).setConverter(fromSportEnum);

        return modelMapper;
    }
}
