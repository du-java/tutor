package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class PriceByPeriod implements Dto{
     Long studentId;
     LocalDateTime startPeriod;
     LocalDateTime endPeriod;
}
