package br.edu.ifsp.arq.trekia.dtos.schedules.create;

import java.math.BigDecimal;

public record CreateScheduleRequestDto(
                String title,
                String description,
                BigDecimal latitude,
                BigDecimal longitude,
                String currencyCode) {
}
