package com.example.tutor.facade;

import com.example.tutor.dto.LessonDto;
import com.example.tutor.mapper.LessonMapper;
import com.example.tutor.models.Course;
import com.example.tutor.models.Lesson;
import com.example.tutor.request.CreateCourseRequest;
import com.example.tutor.services.LessonService;
import com.example.tutor.services.DateService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonFacade {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;
    private final DateService dateService;

    public LessonDto create(LessonDto lessonDto) {
        return lessonMapper.map(lessonService.save(lessonMapper.map(lessonDto)));
    }

    public List<LessonDto> findAll() {
        return lessonService.findAll().stream()
                .map(lessonMapper::map)
                .collect(Collectors.toList());
    }

    public LessonDto findById(Long id) {
        return lessonMapper.map(lessonService.findById(id));
    }

    public void deleteById(Long id) {
        lessonService.deleteById(id);
    }

    public LessonDto update(LessonDto lessonDto) {
        return lessonMapper.map(lessonService.save(lessonMapper.map(lessonDto)));
    }

    public LessonDto changeLesson(LessonDto lessonDto) {
        Lesson lesson = lessonService.findById(lessonDto.getId());
        Course course = lesson.getCourse();
        if (!dateService.isBetween(lessonDto.getStart(), course.getPeriodStart(), course.getPeriodEnd())) {
            throw new IllegalArgumentException("lesson date should be between course start and course end date.");
        }
        lesson.setStart(lessonDto.getStart());
        Lesson saveLesson = lessonService.save(lesson);
        return lessonMapper.map(saveLesson);
    }

    public void addLessons(CreateCourseRequest createCourseRequest, Course course) {
//        if (course.getLessons().size() > 0) {
//            List<Lesson> lessons = course.getLessons();
//            for (Lesson lesson : lessons) {
//                lessonService.deleteById(lesson.getId());
//            }
//            course.setLessons(lessons);
//        }
        final List<Lesson> lessons = lessonService.createLessons(createCourseRequest, course.getGroup().getTutor()).stream()
                .peek(l -> l.setCourse(course))
                .collect(Collectors.toList());
        course.setLessons(lessons);

    }
    //TODO create converter Course to CreateCourseRequest
//    public void addLesson(Course course){
//        courseConverter.
//    }
}
