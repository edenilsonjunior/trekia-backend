package br.edu.ifsp.arq.trekia.dtos.schedules.update;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateScheduleResponseDto(
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
        Integer precipitationChance) {
}