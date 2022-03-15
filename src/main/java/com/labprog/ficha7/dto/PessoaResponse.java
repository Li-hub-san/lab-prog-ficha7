package com.labprog.ficha7.dto;

import com.labprog.ficha7.model.Pessoa;

public class PessoaResponse extends SimpleResponse {
    private Pessoa pessoa;

    public PessoaResponse() {
        this.status = true;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
