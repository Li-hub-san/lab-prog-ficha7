package com.labprog.ficha7.model;

public class Pessoa {
    private static int idCounter;
    private final int id;
    private String nome;
    private int idade;
    private String email;
    private Empresa empresa;

    public Pessoa(String nome, int idade, String email, Empresa empresa) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.empresa = empresa;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", email='" + email + '\'' +
                ", empresa=" + empresa +
                '}';
    }
}