package com.example.tutor.exeptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    int status;
    String error;
    String message;
}
