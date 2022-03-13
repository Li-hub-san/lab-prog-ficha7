package com.labprog.ficha7.controller;

import com.labprog.ficha7.model.Pessoa;
import com.labprog.ficha7.service.ServicePessoa;
import com.labprog.ficha7.sto.SimpleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorPessoa extends ControladorBasico {
    private final ServicePessoa servicePessoa;

    public ControladorPessoa(ServicePessoa servicePessoa) {
        this.servicePessoa = servicePessoa;
    }

    @PostMapping("/addPessoa")
    public ResponseEntity<SimpleResponse> addPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaAdicionada = servicePessoa.addPessoa(pessoa);
            return sucesso(pessoaAdicionada, "Pessoa adicionada com sucesso.");
        } catch (Exception e) {
            return erro(e);
        }
    }

    @PutMapping("/updatePessoa")
    public ResponseEntity<SimpleResponse> updatePessoa(@RequestBody Pessoa pessoa) {
        try {
            return sucesso(servicePessoa.updatePessoa(pessoa), "Pessoa atualizada com sucesso.");
        } catch (Exception e) {
            return erro(e);
        }
    }

    @DeleteMapping("/deletePessoa/{id}")
    public ResponseEntity<SimpleResponse> deletePessoa(@PathVariable int id) {
        try {
            servicePessoa.deletePessoa(id);
            return sucesso(null, "Pessoa apagada com sucesso.");
        } catch (Exception e) {
            return erro(e);
        }
    }

    @GetMapping("/getAllPessoas")
    public ResponseEntity<SimpleResponse> getPessoas() {
        try {
            return sucesso(servicePessoa.getPessoas(), "Pessoas encontradas.");
        } catch (Exception e) {
            return erro(e);
        }
    }

    @GetMapping("/getPessoaById/{id}")
    public ResponseEntity<SimpleResponse> getPessoa(@PathVariable int id) {
        try {
            return sucesso(servicePessoa.getPessoa(id), "Pessoa encontrada.");
        } catch (Exception e) {
            return erro(e);
        }
    }
}
