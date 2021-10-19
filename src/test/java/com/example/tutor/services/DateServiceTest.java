package com.example.tutor.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import com.example.tutor.models.Lesson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DateServiceTest {

    private static final LocalDateTime NOW = LocalDateTime.now();
    private static final LocalDateTime BEFORE = NOW.minusDays(1);
    private static final LocalDateTime AFTER = NOW.plusDays(1);
    private static List<Lesson> lessons;

    private final DateService testObject = new DateService();

    @BeforeAll
    static void initAll() {
        final Lesson lesson1 = Lesson.builder()
                .start(LocalDateTime.parse("2021-09-01T11:30:00"))
                .duration(Duration.ofMinutes(30))
                .build();
        final Lesson lesson2 = Lesson.builder()
                .start(LocalDateTime.parse("2021-09-01T12:30:00"))
                .duration(Duration.ofMinutes(30))
                .build();
        final Lesson lesson3 = Lesson.builder()
                .start(LocalDateTime.parse("2021-09-01T13:30:00"))
                .duration(Duration.ofMinutes(30))
                .build();

        lessons = List.of(lesson1, lesson2, lesson3);
    }

    @Test
    void isBetween_ok_3() {
        assertTrue(testObject.isBetween(NOW, BEFORE, AFTER));
    }

    @Test
    void isBetween_ok_1() {
        assertTrue(testObject.isBetween(NOW, NOW, AFTER));
    }

    @Test
    void isBetween_ok_2() {
        assertTrue(testObject.isBetween(NOW, BEFORE, NOW));
    }

    @Test
    void isBetween_ok_4() {
        assertTrue(testObject.isBetween(NOW, NOW, NOW));
    }

    @Test
    void isBetween_throw_1() {
        assertThrows(NullPointerException.class, () -> testObject.isBetween(NOW, BEFORE, null));
    }

    @Test
    void isBetween_throw_2() {
        assertThrows(NullPointerException.class, () -> testObject.isBetween(NOW, null, AFTER));
    }

    @Test
    void isBetween_throw_3() {
        assertThrows(NullPointerException.class, () -> testObject.isBetween(null, BEFORE, AFTER));
    }

    @Test
    void isBetween_false() {
        assertFalse(testObject.isBetween(NOW, AFTER, BEFORE));
    }

    @Test
    void isFree_busy_1() {
        final Lesson lesson = Lesson.builder()
                .start(LocalDateTime.parse("2021-09-01T12:00:00"))
                .duration(Duration.ofMinutes(30))
                .build();

        final boolean result = testObject.isFree(lessons, lesson);

        assertFalse(result);
    }

    @Test
    void isFree_busy_2() {
        final Lesson lesson = Lesson.builder()
                .start(LocalDateTime.parse("2021-09-01T12:05:00"))
                .duration(Duration.ofMinutes(30))
                .build();

        final boolean result = testObject.isFree(lessons, lesson);

        assertFalse(result);
    }

    @Test
    void isFree_not_busy() {
        final Lesson lesson = Lesson.builder()
                .start(LocalDateTime.parse("2021-09-01T12:05:00"))
                .duration(Duration.ofMinutes(20))
                .build();

        final boolean result = testObject.isFree(lessons, lesson);

        assertTrue(result);
    }
}
