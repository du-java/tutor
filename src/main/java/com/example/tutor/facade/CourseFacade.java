package com.example.tutor.facade;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.mapper.CourseMapper;
import com.example.tutor.models.Course;
import com.example.tutor.models.Lesson;
import com.example.tutor.request.CreateCourseRequest;
import com.example.tutor.services.CourseService;
import com.example.tutor.services.converter.CourseConverter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseFacade {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final LessonFacade lessonFacade;
    private final CourseConverter courseConverter;

    public CourseDto create(CreateCourseRequest createCourseRequest) {
        final Course course = courseConverter.convert(createCourseRequest);
        lessonFacade.addLessons(createCourseRequest,course);
        final Course savedCourse = courseService.save(course);
        return courseConverter.convert(savedCourse);
    }

    public List<CourseDto> findAll() {
        return courseService.findAll().stream()
                .map(courseConverter::convert)
                .collect(Collectors.toList());
    }

    public CourseDto findById(Long id) {
        return courseConverter.convert(courseService.findById(id));
    }

    public void deleteById(Long id) {
        courseService.deleteById(id);
    }

    public CourseDto update(CourseDto courseDto) {
        // todo: if start and end changed remove or add lessons
        return courseConverter.convert(courseService.save(courseConverter.convert(courseDto)));
    }

//    public CourseDto changeCourseDate(CourseDto courseDto) {
//        Course course = courseService.findById(courseDto.getId());
//        LocalDate periodStart = course.getPeriodStart();
//        LocalDate periodStartNew = courseDto.getPeriodStart();
//        LocalDate periodEnd = course.getPeriodEnd();
//        LocalDate periodEndNew = courseDto.getPeriodEnd();
//        if ((!periodStart.equals(periodStartNew) || !periodEnd.equals(periodEndNew)) && isCanChangeDate(courseDto)) {
//
//            course.setPeriodStart(periodStartNew);
//            course.setPeriodEnd(periodEndNew);
//            CreateCourseRequest courseRequest = courseConverter.getCourseRequest(course);
//            lessonFacade.addLessons(courseRequest,course);
//            Course savedCourse = courseService.save(course);
//            return courseConverter.convert(savedCourse);
//        }
//        return courseDto;
//    }

    public CourseDto changeCourseDate(CourseDto courseDto){
        Course course = courseService.findById(courseDto.getId());
        LocalDate periodStart = course.getPeriodStart();
        LocalDate periodStartNew = courseDto.getPeriodStart();
        LocalDate periodEnd = course.getPeriodEnd();
        LocalDate periodEndNew = courseDto.getPeriodEnd();
        if ((!periodStart.equals(periodStartNew) || !periodEnd.equals(periodEndNew)) && isCanChangeDate(courseDto)) {

            CreateCourseRequest courseRequest = courseConverter.getCourseRequest(course);
            List<Lesson> lessons = course.getLessons();
            for (Lesson lesson : lessons) {
                lessonFacade.deleteById(lesson.getId());
            }
            courseService.deleteById(course.getId());
            return create(courseRequest);
        }
        return courseDto;
    }


    private boolean isCanChangeDate(CourseDto courseDto) {
        return LocalDate.now().minusDays(1).isBefore(courseDto.getPeriodStart()) && LocalDate.now().isBefore(courseDto.getPeriodEnd().plusDays(1));

    }


}
