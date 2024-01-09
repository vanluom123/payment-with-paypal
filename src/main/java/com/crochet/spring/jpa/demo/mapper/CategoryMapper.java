package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.payload.dto.CategoryDTO;
import com.crochet.spring.jpa.demo.payload.dto.ghn.order.GHNCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CategoryMapper {
    @Mapping(target = "level1", source = "name")
    GHNCategory toGHNCategory(CategoryDTO category);
}
