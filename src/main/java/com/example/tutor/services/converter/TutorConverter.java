package com.example.tutor.services.converter;

import com.example.tutor.dto.TutorDto;
import com.example.tutor.models.Group;
import com.example.tutor.models.Tutor;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.utils.ListFiller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TutorConverter implements Converter<Tutor, TutorDto> {

    private final GroupService groupService;
    private final ListFiller listFiller;

    @Override
    public Tutor convert(TutorDto tutorDto) {
        final List<Long> groupIds = listFiller.ifNullGetEmptyList(tutorDto.getGroupId());
        return Tutor.builder()
                .id(tutorDto.getId())
                .firstname(tutorDto.getFirstname())
                .lastname(tutorDto.getLastname())
                .groups(groupIds.stream()
                        .map(groupService::findById)
                        .collect(Collectors.toSet())
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
