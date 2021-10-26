package com.example.tutor.services;

import com.example.tutor.models.Lesson;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class DateService {
    public boolean isBetween(@NonNull LocalDateTime shouldBeBetween, @NonNull LocalDateTime before, @NonNull LocalDateTime after) {
        return (shouldBeBetween.isAfter(before) && shouldBeBetween.isBefore(after))
                || shouldBeBetween.equals(before)
                || shouldBeBetween.equals(after);
    }

    public boolean isBetween(@NonNull LocalDateTime shouldBeBetween, @NonNull LocalDate before, @NonNull LocalDate after) {
        return shouldBeBetween.toLocalDate().isAfter(before) && shouldBeBetween.toLocalDate().isBefore(after)
                || shouldBeBetween.toLocalDate().equals(before) || shouldBeBetween.toLocalDate().equals(after);
    }

    public boolean isFree(List<Lesson> lessons, Lesson lesson) {
        return lessons.stream().noneMatch(l ->
                {
                    final boolean start = isBetween(lesson.getStart(), l.getStart(), l.getStart().plus(l.getDuration()));
                    final boolean end = isBetween(lesson.getStart().plus(lesson.getDuration()), l.getStart(), l.getStart().plus(l.getDuration()));
                    return start || end;
                }
        );
    }
}
