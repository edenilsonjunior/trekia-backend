package br.edu.ifsp.arq.trekia.dtos.schedules.update;

import java.math.BigDecimal;

public record UpdateScheduleRequestDto(
                String title,
                String description,
                BigDecimal latitude,
                BigDecimal longitude,
                String currencyCode) {
}
