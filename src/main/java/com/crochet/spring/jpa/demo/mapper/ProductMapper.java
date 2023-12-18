package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {FileMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "files", source = "fileModals")
    ProductResponse productToProductResult(Product product);

    List<ProductResponse> productsToProductResults(Collection<Product> products);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(ProductRequest request, @MappingTarget Product product);
}
