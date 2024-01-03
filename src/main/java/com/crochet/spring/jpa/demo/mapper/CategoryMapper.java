package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Category;
import com.crochet.spring.jpa.demo.payload.dto.ghn.order.GHNCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "level1", source = "name")
    GHNCategory toGHNCategory(Category category);
}
