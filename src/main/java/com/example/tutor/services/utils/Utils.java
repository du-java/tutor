package com.example.tutor.services.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class Utils {
    public  Boolean isBetween(LocalDateTime date, LocalDateTime before, LocalDateTime after) {
        return date.isAfter(before) && date.isBefore(after);
    }
    public  Boolean isBetween(LocalDateTime date, LocalDate before, LocalDate after) {
        return date.toLocalDate().isAfter(before) && date.toLocalDate().isBefore(after);
    }


}
