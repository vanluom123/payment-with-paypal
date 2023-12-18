package com.crochet.spring.jpa.demo.mapper;

import com.crochet.spring.jpa.demo.model.FileModal;
import com.crochet.spring.jpa.demo.payload.response.FileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.util.ObjectUtils;

import java.util.Base64;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    @Mapping(target = "bytes", source = "bytes", qualifiedByName = "decoding")
    FileResponse fileModalToFileResult(FileModal fileModal);

    default List<FileResponse> fileModalToFileResults(List<FileModal> fileModals) {
        if (ObjectUtils.isEmpty(fileModals)) {
            return null;
        }
        return fileModals.stream()
                .map(this::fileModalToFileResult)
                .toList();
    }

    @Named("decoding")
    default byte[] decoding(String bytes) {
        return Base64.getDecoder().decode(bytes);
    }
}
