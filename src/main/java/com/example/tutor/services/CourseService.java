package com.example.tutor.services;

import com.example.tutor.exeptions.NotFoundExeption;
import com.example.tutor.models.Course;
import com.example.tutor.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundExeption(id, "course"));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
