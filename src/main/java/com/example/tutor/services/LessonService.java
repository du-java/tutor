package com.example.tutor.services;

import com.example.tutor.exeptions.BusyTimeException;
import com.example.tutor.exeptions.NotFoundException;
import com.example.tutor.models.Lesson;
import com.example.tutor.models.Tutor;
import com.example.tutor.repositories.LessonRepository;
import com.example.tutor.request.CreateCourseRequest;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonService {

    private static final int ONE_A_WEEK = 7;
    private final LessonRepository lessonRepository;
    private final DateService dateService;

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "lesson"));
    }

    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }

    public Lesson update(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> createLessons(CreateCourseRequest createCourseRequest, Tutor tutor) {
        final DayOfWeek dayOfWeek = DayOfWeek.valueOf(createCourseRequest.getDayOfWeek().toUpperCase(Locale.ROOT));
        LocalDate startDay = findStartDay(createCourseRequest.getStart(), dayOfWeek);
        final List<Lesson> lessons = new ArrayList<>();

        while (startDay.isBefore(createCourseRequest.getEnd())) {
            LocalDateTime startOfLesson = LocalDateTime.of(startDay, createCourseRequest.getLessonStartTime());

            final List<Lesson> lessonListOfTutor = tutor.getGroups().stream()
                    .flatMap(gr -> gr.getCourses().stream())
                    .flatMap(course -> course.getLessons().stream())
                    .collect(Collectors.toList());

            final Lesson lesson = Lesson.builder()
                    .start(startOfLesson)
                    .duration(Duration.ofMinutes(createCourseRequest.getLessonDuration()))
                    .build();

            if (!dateService.isFree(lessonListOfTutor, lesson)) {
                throw new BusyTimeException(startOfLesson);
            }

            lessons.add(lesson);
            startDay = startDay.plusDays(ONE_A_WEEK);
        }
        return lessons;
    }

    private LocalDate findStartDay(@NonNull LocalDate localDate, DayOfWeek dayOfWeek) {
        while (!localDate.getDayOfWeek().equals(dayOfWeek)) {
            localDate = localDate.plusDays(1);
        }
        return localDate;
    }

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

}
