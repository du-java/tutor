package com.example.tutor.facade;

import com.example.tutor.dto.LessonDto;
import com.example.tutor.models.Course;
import com.example.tutor.models.Lesson;
import com.example.tutor.services.CourseService;
import com.example.tutor.services.LessonService;
import com.example.tutor.services.converter.LessonConverter;
import com.example.tutor.services.utils.Utils;
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

    public LessonDto changeLesson(LessonDto lessonDto) {
        Lesson lesson = lessonService.findById(lessonDto.getId());
        Course course = lesson.getCourse();
        if(!Utils.isBetween(lessonDto.getStart(),course.getPeriodStart(),course.getPeriodEnd())){
            throw new IllegalArgumentException("lesson date should be between course start and course end date.");
        }
        lesson.setStart(lessonDto.getStart());
        Lesson saveLesson = lessonService.save(lesson);
        return lessonConverter.convert(saveLesson);
    }
}
