package com.example.tutor.exeptions;

import com.example.tutor.request.CreateCourseRequest;

public class EmptyCourseException extends RuntimeException {
    public EmptyCourseException(CreateCourseRequest createCourseRequest) {
        super("No lessons from " + createCourseRequest.getPeriodStart() + " to " + createCourseRequest.getPeriodEnd());
    }
}
