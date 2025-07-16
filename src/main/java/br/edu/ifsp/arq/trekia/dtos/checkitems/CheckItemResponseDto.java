package br.edu.ifsp.arq.trekia.dtos.checkitems;

public record CheckItemResponseDto(
        Long id,
        String description,
        boolean isChecked
) {
}
