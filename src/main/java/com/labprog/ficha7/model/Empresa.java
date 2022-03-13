package com.labprog.ficha7.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private final int id;
    private static int idCounter = 0;
    private String nome;
    private String morada;
    private int numeroFuncionariosAtual;
    private int numeroFuncionariosDesdeCriacao;
    private final List<Pessoa> pessoas;

    public Empresa(String nome, String morada) {
        this.nome = nome;
        this.morada = morada;
        idCounter++;
        this.id = idCounter;
        pessoas = new ArrayList<>();
    }

    public void contratar(Pessoa pessoa) {
        pessoas.add(pessoa);
        numeroFuncionariosAtual++;
        numeroFuncionariosDesdeCriacao++;
    }

    public void cessarContrato(Pessoa pessoa) {
        pessoas.remove(pessoa);
        numeroFuncionariosAtual--;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getId() {
        return id;
    }

    public int getNumeroFuncionariosAtual() {
        return numeroFuncionariosAtual;
    }

    public int getNumeroFuncionariosDesdeCriacao() {
        return numeroFuncionariosDesdeCriacao;
    }
}
