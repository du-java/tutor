package com.example.tutor.service.utils;

import com.example.tutor.services.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class UtilsTest {

    private static final LocalDateTime NOW = LocalDateTime.now();
    private LocalDateTime before;
    private LocalDateTime after;

    @BeforeEach
    void init() {
        before = NOW.minusDays(1);
        after = NOW.plusDays(1);
    }

    @Test
    void isBetweenTest() {
        Boolean result = Utils.isBetween(NOW, before, after);

        Assertions.assertTrue(result);
    }

    @Test
    void isBetweenIfAttributeNullTest() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            Utils.isBetween(NOW, before, null);
        });

        Assertions.assertNotNull(exception);
    }

    @Test
    void isBetweenTest2() {
        Boolean result = Utils.isBetween(NOW, after, before);

        Assertions.assertFalse(result);
    }
}
