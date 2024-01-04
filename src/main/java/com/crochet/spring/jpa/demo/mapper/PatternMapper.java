package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Pattern;
import com.crochet.spring.jpa.demo.payload.response.PatternResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatternMapper {
    PatternResponse toResponse(Pattern pattern);

    List<PatternResponse> toResponses(Collection<Pattern> patterns);
}
