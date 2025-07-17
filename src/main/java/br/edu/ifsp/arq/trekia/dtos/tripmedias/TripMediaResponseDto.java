package br.edu.ifsp.arq.trekia.dtos.tripmedias;

public record TripMediaResponseDto(
        Long id,
        byte[] media,
        String description
) {
}
