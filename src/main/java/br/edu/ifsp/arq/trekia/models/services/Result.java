package br.edu.ifsp.arq.trekia.models.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class Result {

    public static <T> ResponseEntity<?> toResponse(
            T data,
            String message,
            HttpStatus status
    ) {
        var map = new HashMap<String, Object>();

        map.put("data", data);
        map.put("message", message);
        map.put("status", status);

        return new ResponseEntity<>(map, status);
    }

    public static <T> ResponseEntity<?> toResponse(
            T data,
            HttpStatus status
    ) {
        return toResponse(data, null, status);
    }

    public static ResponseEntity<?> toResponse(
            String message,
            HttpStatus status
    ) {
        return toResponse(null, message, status);
    }
}