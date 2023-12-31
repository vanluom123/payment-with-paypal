package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import com.crochet.spring.jpa.demo.payload.response.OrderProductDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CustomerMapper.class, ProductMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    default OrderProductDetailResponse orderDetailToOrderResult(OrderProductDetail orderProductDetail) {
        return OrderProductDetailResponse.builder()
                .orderDate(orderProductDetail.getOrderDate().toString())
                .quantity(orderProductDetail.getQuantity())
                .totalPrice(orderProductDetail.getPrice())
                .build();
    }
}
