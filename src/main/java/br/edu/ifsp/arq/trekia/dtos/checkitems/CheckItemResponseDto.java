package br.edu.ifsp.arq.trekia.dtos.checkitems;

public record CheckItemResponseDto(
        long id,
        String description,
        boolean isChecked
) {
}
