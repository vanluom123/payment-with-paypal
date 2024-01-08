package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.Contact;
import com.crochet.spring.jpa.demo.payload.response.ContactResponse;
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
    ContactResponse toResponse(Contact contact);

    List<ContactResponse> toResponses(Collection<Contact> contacts);
}
