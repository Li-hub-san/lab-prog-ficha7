package com.labprog.ficha7.utils;

import com.labprog.ficha7.dto.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {

    public static ResponseEntity<SimpleResponse> sucesso(SimpleResponse sr) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sr);
    }

    public static ResponseEntity<SimpleResponse> erro(SimpleException e) {
        SimpleResponse sr = new SimpleResponse();
        sr.setMessage(e.getMessage());

        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (e.getCode() == ExceptionCode.NAO_ENCONTRADO) {
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity
                .status(status)
                .body(sr);
    }

}
