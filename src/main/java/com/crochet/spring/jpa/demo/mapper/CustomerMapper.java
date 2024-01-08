package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {OrderMapper.class})
public interface CustomerMapper {
    CustomerResponse toResponse(Customer customer);

    Customer toEntity(CustomerResponse response);

    List<CustomerResponse> toResponses(Collection<Customer> customers);

    @Named("stringToUUID")
    default UUID stringToUUID(String id) {
        if (id == null) {
            return null;
        }
        return UUID.fromString(id);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(CustomerRequest customerRequest, @MappingTarget Customer customer);
}
