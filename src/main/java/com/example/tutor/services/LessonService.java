package com.example.tutor.services;

import com.example.tutor.dto.CreateCourseRequest;
import com.example.tutor.exeptions.NotFoundExeption;
import com.example.tutor.models.Lesson;
import com.example.tutor.repositories.LessonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final StudentService studentService;


    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new NotFoundExeption(id, "lesson"));
    }

    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }

    public Lesson update(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> createLessons(CreateCourseRequest createCourseRequest) {
        LocalDate startDay = findStartDay(createCourseRequest.getStart(), createCourseRequest.getDayOfWeek());
        LocalDate lessonDay = startDay;
        List<Lesson> lessons = new ArrayList<>();


        while (lessonDay.isBefore(createCourseRequest.getEnd())) {
            Lesson lesson = Lesson.builder()
                    .start(LocalDateTime.of(lessonDay, createCourseRequest.getLessonStartTime()))
                    .duration(Duration.ofMinutes(createCourseRequest.getLessonDuration()))
                    .build();
            lessons.add(lesson);
        }
        return lessons;
    }

    private LocalDate findStartDay(@NonNull LocalDate localDate, DayOfWeek dayOfWeek) {
        while (localDate.getDayOfWeek().equals(dayOfWeek)) {
            localDate = localDate.plusDays(1);
        }
        return localDate;
    }

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }
}
