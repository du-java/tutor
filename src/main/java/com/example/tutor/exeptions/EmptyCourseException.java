package com.example.tutor.exeptions;

import com.example.tutor.dto.CreateCourseRequest;

public class EmptyCourseException extends RuntimeException {
    public EmptyCourseException(CreateCourseRequest createCourseRequest) {
        super("No lessons from " + createCourseRequest.getStart() + " to " + createCourseRequest.getEnd());
    }
}
