package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderMapper.class})
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

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
