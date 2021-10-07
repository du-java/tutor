package com.example.tutor.services.converter;

import com.example.tutor.dto.TutorDto;
import com.example.tutor.models.Group;
import com.example.tutor.models.Tutor;
import com.example.tutor.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TutorConverter implements Converter<Tutor, TutorDto> {

    private final GroupService groupService;

    @Override
    public Tutor convert(TutorDto tutorDto) {
        return Tutor.builder()
                .id(tutorDto.getId())
                .firstname(tutorDto.getFirstname())
                .lastname(tutorDto.getLastname())
                .group(tutorDto.getGroupId().stream()
                        .map(groupService::findById)
                        .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public TutorDto convert(Tutor tutor) {
        return TutorDto.builder()
                .id(tutor.getId())
                .firstname(tutor.getFirstname())
                .lastname(tutor.getLastname())
                .groupId(tutor.getGroup().stream()
                        .map(Group::getId)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
