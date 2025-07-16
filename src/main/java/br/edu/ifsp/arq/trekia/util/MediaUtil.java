package br.edu.ifsp.arq.trekia.util;

import br.edu.ifsp.arq.trekia.exceptions.ImageException;
import org.springframework.web.multipart.MultipartFile;

public class MediaUtil {

    public static byte[] convertMultipartFileToBytes(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ImageException("Arquivo está vazio ou nulo.", null);
        }
        try {
            return file.getBytes();
        } catch (Exception e) {
            throw new ImageException("Erro ao converter arquivo MultipartFile para bytes", e);
        }
    }
}