package com.labprog.ficha7.model;

import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String morada;
    private int numeroFuncionariosAtual;
    private int numeroFuncionariosDesdeCriacao;

    @Builder.Default
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Pessoa> pessoas = new ArrayList<>();

    public Empresa(String nome, String morada) {
        this.nome = nome;
        this.morada = morada;
    }

    public Empresa() {
    }

    public void aumentarFuncionariosAtuais() {
        numeroFuncionariosAtual++;
        numeroFuncionariosDesdeCriacao++;
    }

    public void reduzirFuncionariosAtuais() {
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

    public Long getId() {
        return id;
    }

    public int getNumeroFuncionariosAtual() {
        return numeroFuncionariosAtual;
    }

    public int getNumeroFuncionariosDesdeCriacao() {
        return numeroFuncionariosDesdeCriacao;
    }
}
