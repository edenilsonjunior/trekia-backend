package br.edu.ifsp.arq.trekia.dtos.dashboard;

import java.math.BigDecimal;

public record DashboardScheduleDto(
        Long id,
        String title,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
