package br.edu.ifsp.arq.trekia.dtos.schedules.create;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateScheduleResponseDto(
        Long id,
        String tripName,
        LocalDateTime plannedAt,
        String title,
        String description,
        BigDecimal latitude,
        BigDecimal longitude,
        BigDecimal currentLocalBalance,
        String currencyCode,
        BigDecimal minTemperature,
        BigDecimal maxTemperature,
        Integer precipitationChance
) {}