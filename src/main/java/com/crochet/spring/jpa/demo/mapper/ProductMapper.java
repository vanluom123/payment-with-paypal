package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.dto.ProductDTO;
import com.crochet.spring.jpa.demo.dto.ghn.order.GHNItem;
import com.crochet.spring.jpa.demo.model.Product;
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
    @Mapping(target = "categoryDTO", source = "category")
    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTOs(Collection<Product> products);

    @Mapping(target = "code", source = "id")
    @Mapping(target = "category", source = "categoryDTO")
    GHNItem toGHNItem(ProductDTO product);

    List<GHNItem> toGHNItems(Collection<ProductDTO> products);
}
