package com.example.tutor.exeptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BusyTimeException extends RuntimeException {
    private final LocalDateTime localDateTime;

    public BusyTimeException(LocalDateTime localDateTime) {
        super("is busy "  + localDateTime);
        this.localDateTime = localDateTime;
    }
}
