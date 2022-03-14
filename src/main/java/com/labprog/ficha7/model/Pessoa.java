package com.labprog.ficha7.model;

import com.labprog.ficha7.repository.PessoaRepository;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "Pessoa")
public class Pessoa {
    protected static int idCounter;
    protected final int id;
    protected String nome;
    protected int idade;
    protected String email;
    protected final int empresaId;

    public Pessoa(String nome, int idade, String email, int empresaId) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.empresaId = empresaId;
        idCounter++;
        this.id = idCounter;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", email='" + email + '\'' +
                ", empresa=" + empresaId +
                '}';
    }
}