package br.edu.ifsp.arq.trekia.dtos.trips;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;

import java.time.LocalDate;

@Valid
public record UpdateTripRequestDto(
        String title,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate startDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate endDate
) {
}
