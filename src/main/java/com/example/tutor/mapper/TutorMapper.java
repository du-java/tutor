package com.example.tutor.mapper;

import com.example.tutor.dto.TutorDto;
import com.example.tutor.models.Tutor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {GroupsMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface TutorMapper {

    TutorDto map(Tutor entity);

    Tutor map(TutorDto dto);
}
