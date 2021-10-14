package com.example.tutor.respons;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class PayByLessonsResponse {
    Long studentId;
    LocalDateTime startPeriod;
    LocalDateTime endPeriod;
    BigDecimal costByPeriod;
    List<Long> lessonsForPay;
}
