package com.example.tutor.services.converter;

import com.example.tutor.dto.TutorDto;
import com.example.tutor.models.Group;
import com.example.tutor.models.Tutor;
import com.example.tutor.models.Lesson;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TutorConverter implements Converter<Tutor, TutorDto> {

    private final LessonService lessonService;
    private final GroupService groupService;

    @Override
    public Tutor convert(TutorDto tutorDto) {
        return Tutor.builder()
                .id(tutorDto.getId())
                .firstname(tutorDto.getFirstname())
                .lastname(tutorDto.getLastname())
                .group(tutorDto.getGroup().stream()
                        .map(groupService::findById)
                        .collect(Collectors.toList()))
                .build();


    }

    @Override
    public TutorDto convert(Tutor tutor) {
        return TutorDto.builder()
                .id(tutor.getId())
                .firstname(tutor.getFirstname())
                .lastname(tutor.getLastname())
                .group(tutor.getGroup().stream()
                        .map(Group::getId)
                        .collect(Collectors.toList()))
                .build();

    }
}
