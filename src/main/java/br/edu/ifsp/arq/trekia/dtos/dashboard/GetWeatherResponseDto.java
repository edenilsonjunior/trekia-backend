package br.edu.ifsp.arq.trekia.dtos.dashboard;

import java.math.BigDecimal;

public record GetWeatherResponseDto(
        BigDecimal minTempC,
        BigDecimal maxTempC,
        int maxChanceOfRain
) {
}