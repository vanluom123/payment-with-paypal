package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderMapper.class})
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse customerToCustomerResult(Customer customer);

    List<CustomerResponse> toResults(Collection<Customer> customers);

    @Mapping(target = "id", source = "id", qualifiedByName = "stringToUUID")
    Customer customerRequestToCustomer(CustomerRequest request);

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
        customer.getOrders().forEach(order -> order.setCustomer(customer));
    }
}
