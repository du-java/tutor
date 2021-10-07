package com.example.tutor.facade;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.dto.CreateCourseRequest;
import com.example.tutor.models.Course;
import com.example.tutor.services.CourseService;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.LessonService;
import com.example.tutor.services.converter.CourseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseFacade {

    private final CourseService courseService;
    private final CourseConverter courseConverter;
    private final GroupService groupService;
    private final LessonService lessonService;

    public CourseDto create(CreateCourseRequest createCourseRequest) {
        Course course = Course.builder()
                .periodStart(createCourseRequest.getStart())
                .periodEnd(createCourseRequest.getEnd())
                .group(groupService.findById(createCourseRequest.getGroupId()))
                .lessons(lessonService.createLessons(createCourseRequest))
                .build();

        final Course saved = courseService.save(course);
        return courseConverter.convert(saved);
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
        return courseConverter.convert(courseService.save(courseConverter.convert(courseDto)));
    }
}
