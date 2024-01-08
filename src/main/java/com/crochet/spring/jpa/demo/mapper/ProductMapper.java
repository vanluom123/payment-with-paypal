package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.dto.ghn.order.GHNItem;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mapping(target = "categoryResponse", source = "category")
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponses(Collection<Product> products);

    @Mapping(target = "code", source = "id")
    @Mapping(target = "category", source = "categoryResponse")
    GHNItem toGHNItem(ProductResponse product);

    List<GHNItem> toGHNItems(Collection<ProductResponse> products);
}
