package com.labprog.ficha7.utils;

import com.labprog.ficha7.dto.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
    
    public static ResponseEntity<SimpleResponse> sucesso(String message) {
        SimpleResponse sr = new SimpleResponse(true, message);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sr);
    }

    public static ResponseEntity<SimpleResponse> erro(Exception e) {
        SimpleResponse sr = new SimpleResponse(
                false,
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(sr);
    }

}
