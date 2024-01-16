package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.dto.ShopDTO;
import com.crochet.spring.jpa.demo.model.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShopMapper {
    ShopDTO toDTO(Shop shop);

    List<ShopDTO> toDTOs(Collection<Shop> contacts);
}
