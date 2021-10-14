package com.example.tutor.request;

import com.example.tutor.dto.Dto;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Value
@Builder
public class PriceByPeriod implements Dto {
    @NotNull
    @Positive
    Long studentId;
    @NotNull
    LocalDateTime startPeriod;
    @NotNull
    LocalDateTime endPeriod;
}
