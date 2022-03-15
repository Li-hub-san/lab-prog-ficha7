package com.labprog.ficha7.controller;

import com.labprog.ficha7.dto.PessoaResponse;
import com.labprog.ficha7.dto.PessoasResponse;
import com.labprog.ficha7.dto.SimpleResponse;
import com.labprog.ficha7.model.Pessoa;
import com.labprog.ficha7.service.ServicePessoa;
import com.labprog.ficha7.utils.SimpleException;
import com.labprog.ficha7.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorPessoa {
    private final ServicePessoa servicePessoa;

    public ControladorPessoa(ServicePessoa servicePessoa) {
        this.servicePessoa = servicePessoa;
    }

    @PostMapping("/addPessoa")
    public ResponseEntity<SimpleResponse> addPessoa(@RequestBody Pessoa pessoa) {
        try {
            PessoaResponse pr = new PessoaResponse();
            pr.setPessoa(servicePessoa.addPessoa(pessoa));
            pr.setMessage("Pessoa adicionada com sucesso.");

            return Utils.sucesso(pr);
        } catch (SimpleException e) {
            return Utils.erro(e);
        }
    }

    @PutMapping("/updatePessoa")
    public ResponseEntity<SimpleResponse> updatePessoa(@RequestBody Pessoa pessoa) {
        try {
            PessoaResponse pr = new PessoaResponse();
            pr.setPessoa(servicePessoa.updatePessoa(pessoa));
            pr.setMessage("Pessoa atualizada com sucesso.");
            return Utils.sucesso(pr);
        } catch (SimpleException e) {
            return Utils.erro(e);
        }
    }

    @DeleteMapping("/deletePessoa/{id}")
    public ResponseEntity<SimpleResponse> deletePessoa(@PathVariable Long id) {
        try {
            SimpleResponse sr = new SimpleResponse();
            sr.setMessage("Pessoa apagada com sucesso.");
            sr.setStatus(true);
            servicePessoa.deletePessoa(id);
            return Utils.sucesso(sr);
        } catch (SimpleException e) {
            return Utils.erro(e);
        }
    }

    @GetMapping("/getAllPessoas")
    public ResponseEntity<SimpleResponse> getPessoas() {
        PessoasResponse pr = new PessoasResponse();
        pr.setPessoas(servicePessoa.getPessoas());
        pr.setMessage("Pessoas encontradas.");

        return Utils.sucesso(pr);
    }

    @GetMapping("/getPessoaById/{id}")
    public ResponseEntity<SimpleResponse> getPessoa(@PathVariable Long id) {
        try {
            PessoaResponse pr = new PessoaResponse();
            pr.setPessoa(servicePessoa.getPessoa(id));
            pr.setMessage("Pessoa encontrada.");

            return Utils.sucesso(pr);
        } catch (SimpleException e) {
            return Utils.erro(e);
        }
    }
}
