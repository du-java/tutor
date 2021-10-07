package com.example.tutor.facade;

import com.example.tutor.dto.LessonDto;
import com.example.tutor.services.LessonService;
import com.example.tutor.services.converter.LessonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonFacade {

    private final LessonService lessonService;
    private final LessonConverter lessonConverter;

    public LessonDto create(LessonDto lessonDto) {
        return lessonConverter.convert(lessonService.save(lessonConverter.convert(lessonDto)));
    }

    public List<LessonDto> findAll() {
        return lessonService.findAll().stream()
                .map(lessonConverter::convert)
                .collect(Collectors.toList());
    }

    public LessonDto findById(Long id) {
        return lessonConverter.convert(lessonService.findById(id));
    }

    public void deleteById(Long id) {
        lessonService.deleteById(id);
    }

    public LessonDto update(LessonDto lessonDto) {
        return lessonConverter.convert(lessonService.save(lessonConverter.convert(lessonDto)));
    }
}
