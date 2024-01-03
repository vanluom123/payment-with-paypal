package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.dto.ghn.order.GHNItem;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CategoryMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponses(Collection<Product> products);

    @Mapping(target = "code", source = "id")
    GHNItem toGHNItem(Product product);

    List<GHNItem> toGHNItems(Collection<Product> products);
}
