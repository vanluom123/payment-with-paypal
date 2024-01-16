package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.dto.PatternDTO;
import com.crochet.spring.jpa.demo.model.Pattern;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatternMapper {
  PatternDTO toDTO(Pattern pattern);

  List<PatternDTO> toDTOs(Collection<Pattern> patterns);
}
