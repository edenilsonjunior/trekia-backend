package br.edu.ifsp.arq.trekia.dtos.tripmedias;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateTripMediaRequestDto{
    MultipartFile media;
    String description;
}
