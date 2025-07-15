package br.edu.ifsp.arq.trekia.dtos.checkitems;

import jakarta.validation.Valid;

@Valid
public record CreateCheckItemRequestDto(
        String description
) {
}
