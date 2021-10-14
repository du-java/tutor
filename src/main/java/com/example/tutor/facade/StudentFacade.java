package com.example.tutor.facade;

import com.example.tutor.request.PriceByPeriod;
import com.example.tutor.dto.StudentDto;
import com.example.tutor.models.Lesson;
import com.example.tutor.models.Student;
import com.example.tutor.respons.PayByLessonsResponse;
import com.example.tutor.services.LessonService;
import com.example.tutor.services.StudentService;
import com.example.tutor.services.converter.StudentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentFacade {

    private final StudentService studentService;
    private final LessonService lessonService;
    private final StudentConverter studentConverter;

    public StudentDto create(StudentDto studentDto) {
        return studentConverter.convert(studentService.save(studentConverter.convert(studentDto)));
    }

    public List<StudentDto> findAll() {
        return studentService.findAll().stream()
                .map(studentConverter::convert)
                .collect(Collectors.toList());
    }

    public StudentDto findById(Long id) {
        return studentConverter.convert(studentService.findById(id));
    }

    public void deleteById(Long id) {
        studentService.deleteById(id);
    }

    public StudentDto update(StudentDto studentDto) {
        return studentConverter.convert(studentService.save(studentConverter.convert(studentDto)));
    }

    public StudentDto setVisitedLesson(Long studentId, Long lessonId) {
        final Student student = studentService.findById(studentId);
        final Lesson lesson = lessonService.findById(lessonId);
        student.getVisitedLessons().add(lesson);
        return studentConverter.convert(studentService.update(student));
    }

    public StudentDto updateVisitedLessons(StudentDto studentDto) {
        final Student student = studentService.findById(studentDto.getId());
        final List<Lesson> lessons = studentDto.getVisitedLessons().stream()
                .map(lessonService::findById)
                .collect(Collectors.toList());
        student.getVisitedLessons().addAll(lessons);
        return studentConverter.convert(studentService.update(student));
    }

    public PayByLessonsResponse getPriceByLessons(PriceByPeriod priceByPeriod) {

        Student student = studentService.findById(priceByPeriod.getStudentId());
        List<Long> visitedLessons = student.getVisitedLessons().stream()
                .filter(visitedLesson -> isBetween(priceByPeriod, visitedLesson))
                .map(Lesson::getId)
                .collect(Collectors.toList());
       return PayByLessonsResponse.builder()
                .startPeriod(priceByPeriod.getStartPeriod())
                .endPeriod(priceByPeriod.getEndPeriod())
                .costByPeriod(student.getPrice().multiply(BigDecimal.valueOf(visitedLessons.size())))
                .studentId(priceByPeriod.getStudentId())
                .lessonsForPay(visitedLessons)
                .build();
    }

    private boolean isBetween(PriceByPeriod priceByPeriod, Lesson visitedLesson) {
        return visitedLesson.getStart().toLocalDate().equals(priceByPeriod.getStartPeriod().toLocalDate())
                || visitedLesson.getStart().toLocalDate().equals(priceByPeriod.getEndPeriod().toLocalDate())
                || (visitedLesson.getStart().isBefore(priceByPeriod.getEndPeriod())
                && (visitedLesson.getStart().isAfter(priceByPeriod.getStartPeriod())));
    }
}
