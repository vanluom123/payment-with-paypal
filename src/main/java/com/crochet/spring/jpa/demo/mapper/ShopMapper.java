package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Shop;
import com.crochet.spring.jpa.demo.payload.response.ShopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShopMapper {
    ShopResponse toResponse(Shop shop);

    List<ShopResponse> toResponses(Collection<Shop> contacts);
}
