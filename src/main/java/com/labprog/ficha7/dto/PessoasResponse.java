package com.labprog.ficha7.dto;

import com.labprog.ficha7.model.Pessoa;

import java.util.List;

public class PessoasResponse extends SimpleResponse {
    private List<Pessoa> pessoas;

    public PessoasResponse() {
        this.status = true;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

}
