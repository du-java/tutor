package com.example.tutor.facade;

import com.example.tutor.dto.StudentDto;
import com.example.tutor.mapper.StudentMapper;
import com.example.tutor.models.Lesson;
import com.example.tutor.models.Student;
import com.example.tutor.request.PriceByPeriod;
import com.example.tutor.respons.PayByLessonsResponse;
import com.example.tutor.services.LessonService;
import com.example.tutor.services.StudentService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.example.tutor.services.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentFacade {

    private final StudentService studentService;
    private final LessonService lessonService;
    private final DateService dateService;
    private final StudentMapper studentMapper;

    public StudentDto create(StudentDto studentDto) {
        return studentMapper.map(studentService.save(studentMapper.map(studentDto)));
    }

    public List<StudentDto> findAll() {
        return studentService.findAll().stream()
                .map(studentMapper::map)
                .collect(Collectors.toList());
    }

    public StudentDto findById(Long id) {
        return studentMapper.map(studentService.findById(id));
    }

    public void deleteById(Long id) {
        studentService.deleteById(id);
    }

    public StudentDto update(StudentDto studentDto) {
        return studentMapper.map(studentService.save(studentMapper.map(studentDto)));
    }

    public StudentDto setVisitedLesson(Long studentId, Long lessonId) {
        final Student student = studentService.findById(studentId);
        final Lesson lesson = lessonService.findById(lessonId);
        student.getVisitedLessons().add(lesson);
        return studentMapper.map(studentService.update(student));
    }

    public StudentDto updateVisitedLessons(StudentDto studentDto) {
        final Student student = studentService.findById(studentDto.getId());
        final List<Lesson> lessons = studentDto.getVisitedLessons().stream()
                .map(lessonService::findById)
                .collect(Collectors.toList());
        student.getVisitedLessons().addAll(lessons);
        return studentMapper.map(studentService.update(student));
    }

    public PayByLessonsResponse getPriceByLessons(PriceByPeriod priceByPeriod) {
        Student student = studentService.findById(priceByPeriod.getStudentId());
        List<Long> visitedLessons = student.getVisitedLessons().stream()
                .filter(visitedLesson -> dateService.isBetween(
                        visitedLesson.getStart(),
                        priceByPeriod.getStartPeriod(),
                        priceByPeriod.getEndPeriod()
                ))
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
}
