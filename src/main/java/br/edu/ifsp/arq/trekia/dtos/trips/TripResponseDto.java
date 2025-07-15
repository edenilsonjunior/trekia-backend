package br.edu.ifsp.arq.trekia.dtos.trips;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record TripResponseDto(
        long id,
        String title,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate startDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate endDate
) {
}
