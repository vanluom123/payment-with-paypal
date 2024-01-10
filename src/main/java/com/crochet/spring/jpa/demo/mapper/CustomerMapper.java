package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.dto.CustomerDTO;
import com.crochet.spring.jpa.demo.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {OrderMapper.class}
)
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO response);

    List<CustomerDTO> toDTOs(Collection<Customer> customers);
}
