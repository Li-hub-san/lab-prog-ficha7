package com.labprog.ficha7.controller;

import com.labprog.ficha7.model.Pessoa;
import com.labprog.ficha7.service.ServicePessoa;
import com.labprog.ficha7.dto.SimpleResponse;
import com.labprog.ficha7.utils.Utils;
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
    public ResponseEntity<SimpleResponse> addPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaAdicionada = servicePessoa.addPessoa(pessoa);
            return Utils.sucesso("Pessoa adicionada com sucesso.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

    @PutMapping("/updatePessoa")
    public ResponseEntity<SimpleResponse> updatePessoa(@RequestBody Pessoa pessoa) {
        try {
            servicePessoa.updatePessoa(pessoa);
            return Utils.sucesso("Pessoa atualizada com sucesso.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

    @DeleteMapping("/deletePessoa/{id}")
    public ResponseEntity<SimpleResponse> deletePessoa(@PathVariable int id) {
        try {
            servicePessoa.deletePessoa(id);
            return Utils.sucesso("Pessoa apagada com sucesso.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

    @GetMapping("/getAllPessoas")
    public ResponseEntity<SimpleResponse> getPessoas() {
        try {
            List<Pessoa> pessoas = servicePessoa.getPessoas();
            return Utils.sucesso("Pessoas encontradas.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

    @GetMapping("/getPessoaById/{id}")
    public ResponseEntity<SimpleResponse> getPessoa(@PathVariable int id) {
        try {
            servicePessoa.getPessoa(id);
            return Utils.sucesso("Pessoa encontrada.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }
}
