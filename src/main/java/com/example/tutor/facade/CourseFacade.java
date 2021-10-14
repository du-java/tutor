package com.example.tutor.facade;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.request.CreateCourseRequest;
import com.example.tutor.models.Course;
import com.example.tutor.services.CourseService;
import com.example.tutor.services.converter.CourseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseFacade {

    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public CourseDto create(CreateCourseRequest createCourseRequest) {
        final Course course = courseConverter.convert(createCourseRequest);
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
        // todo: if start and end changed remove or add lessons
        return courseConverter.convert(courseService.save(courseConverter.convert(courseDto)));
    }
}
