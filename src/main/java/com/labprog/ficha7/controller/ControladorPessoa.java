package com.labprog.ficha7.controller;

import com.labprog.ficha7.model.Pessoa;
import com.labprog.ficha7.service.ServicePessoa;
import com.labprog.ficha7.sto.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorPessoa {
    private final ServicePessoa servicePessoa;

    public ControladorPessoa(ServicePessoa servicePessoa) {
        this.servicePessoa = servicePessoa;
    }

    @PostMapping("/addPessoa")
    public ResponseEntity<SimpleResponse<Pessoa>> addPessoa(@RequestBody Pessoa pessoa) {
        SimpleResponse<Pessoa> sr = new SimpleResponse<>(
                true,
                "Pessoa adicionada com sucesso",
                servicePessoa.addPessoa(pessoa)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sr);
    }

    @PutMapping("/updatePessoa")
    public Pessoa updatePessoa(@RequestBody Pessoa pessoa) throws Exception {
        return servicePessoa.updatePessoa(pessoa);
    }

    @DeleteMapping("/deletePessoa/{id}")
    public Boolean deletePessoa(@PathVariable String id) {
        return servicePessoa.deletePessoa(id);
    }

    @GetMapping("/getAllPessoas")
    public List<Pessoa> getPessoas() {
        return servicePessoa.getPessoas();
    }

    @GetMapping("/getPessoaById/{id}")
    public Pessoa getPessoa(@PathVariable String id) throws Exception {
        return servicePessoa.getPessoa(id);
    }

}
