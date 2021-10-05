package com.example.tutor.exeptions;

import lombok.Getter;

@Getter
public class NotFoundExeption extends RuntimeException {
    private final String msg;
    private final Long id;

    public NotFoundExeption(Long id, String tutor) {
        this.msg = tutor;
        this.id = id;
    }
}
