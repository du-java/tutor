package com.example.tutor.services.converter;

import com.example.tutor.dto.CreateTutorDto;
import com.example.tutor.dto.TutorDto;
import com.example.tutor.models.Group;
import com.example.tutor.models.Tutor;
import com.example.tutor.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TutorConverter implements Converter<Tutor, TutorDto> {

    private final GroupService groupService;

    public Tutor convert(CreateTutorDto tutorDto) {
        return Tutor.builder()
                .firstname(tutorDto.getFirstname())
                .lastname(tutorDto.getLastname())
                .password(tutorDto.getPassword())
                .email(tutorDto.getEmail())
                .groups(Collections.emptyList())
                .build();
    }

    @Override
    public Tutor convert(TutorDto tutorDto) {
        return Tutor.builder()
                .id(tutorDto.getId())
                .firstname(tutorDto.getFirstname())
                .lastname(tutorDto.getLastname())
                .groups(tutorDto.getGroupId().stream()
                        .map(groupService::findById)
                        .collect(Collectors.toList())
                )
                .password(tutorDto.getPassword())
                .email(tutorDto.getEmail())
                .build();
    }

    @Override
    public TutorDto convert(Tutor tutor) {
        return TutorDto.builder()
                .id(tutor.getId())
                .firstname(tutor.getFirstname())
                .lastname(tutor.getLastname())
                .groupId(tutor.getGroups().stream()
                        .map(Group::getId)
                        .collect(Collectors.toList())
                )
                .email(tutor.getEmail())
                .build();
    }
}
