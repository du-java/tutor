package com.example.tutor.exeptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String msg;
    private final Long id;

    public NotFoundException(Long id, String msg) {
        super("Not found " + msg + " with ID " + id);
        this.msg = msg;
        this.id = id;
    }
}
