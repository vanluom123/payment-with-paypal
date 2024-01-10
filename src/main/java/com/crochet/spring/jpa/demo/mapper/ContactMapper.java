package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.dto.ContactDTO;
import com.crochet.spring.jpa.demo.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ContactMapper {
    ContactDTO toDTO(Contact contact);

    List<ContactDTO> toDTOs(Collection<Contact> contacts);
}
