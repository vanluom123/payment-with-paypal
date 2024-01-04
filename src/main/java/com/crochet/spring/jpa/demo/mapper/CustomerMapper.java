package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;
import org.mapstruct.AfterMapping;
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
    CustomerResponse customerToCustomerResult(Customer customer);

    List<CustomerResponse> toResults(Collection<Customer> customers);

    @Named("stringToUUID")
    default UUID stringToUUID(String id) {
        if (id == null) {
            return null;
        }
        return UUID.fromString(id);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(CustomerRequest customerRequest, @MappingTarget Customer customer);

    @AfterMapping
    default void linkOrders(@MappingTarget Customer customer) {
        customer.getOrderPatterns().forEach(order -> order.setCustomer(customer));
    }
}
