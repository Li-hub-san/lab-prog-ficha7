package com.labprog.ficha7.controller;

import com.labprog.ficha7.sto.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControladorBasico {

    protected ResponseEntity<SimpleResponse> sucesso(Object data, String message) {
        SimpleResponse sr = new SimpleResponse(
                true,
                message,
                data
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sr);
    }

    protected ResponseEntity<SimpleResponse> erro(Exception e) {
        SimpleResponse sr = new SimpleResponse(
                false,
                e.getMessage(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(sr);
    }

}
