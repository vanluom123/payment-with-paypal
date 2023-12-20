package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Pattern;
import com.crochet.spring.jpa.demo.payload.response.PatternResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatternMapper {
    PatternMapper INSTANCE = Mappers.getMapper(PatternMapper.class);

    PatternResponse toResponse(Pattern pattern);

    List<PatternResponse> toResponses(Collection<Pattern> patterns);
}
