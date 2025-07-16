package br.edu.ifsp.arq.trekia.dtos.dashboard;

import java.util.List;

public record DashboardTripDto(
        Long id,
        String title,
        String description,
        String mediaBase64,
        String startDate,
        String endDate,
        String createdAt,
        List<DashboardScheduleDto> schedules
) {
}
