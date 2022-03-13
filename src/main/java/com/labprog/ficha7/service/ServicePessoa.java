package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.model.Pessoa;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePessoa {
    public List<Pessoa> pessoas = new ArrayList<>();
    private final ServiceEmpresa serviceEmpresa;

    public ServicePessoa(@Lazy ServiceEmpresa serviceEmpresa) {
        this.serviceEmpresa = serviceEmpresa;
    }

    public Pessoa addPessoa(Pessoa pessoa) throws Exception {
        if (pessoa.getIdade() < 16 || pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new Exception("Campos incompletos");
        }

        // validação se a empresa existe ou não está feita dentro de getEmpresa -> menos uma validação neste método.
        Empresa empresa = serviceEmpresa.getEmpresa(pessoa.getEmpresaId());
        empresa.contratar(pessoa);

        pessoas.add(pessoa);

        return pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Pessoa getPessoa(int id) throws Exception {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        throw new Exception("Pessoa com o id " + id + " inexistente.");
    }

    public void deletePessoa(int id) throws Exception {
        Pessoa pessoa = getPessoa(id);
        pessoas.remove(pessoa);
        Empresa empresa = serviceEmpresa.getEmpresa(pessoa.getEmpresaId());
        empresa.cessarContrato(pessoa);
    }

    public Pessoa updatePessoa(Pessoa pessoa) throws Exception {
        Pessoa pessoaDb = getPessoa(pessoa.getId());

        if (pessoa.getIdade() >= 16) {
            pessoaDb.setIdade(pessoa.getIdade());
        }

        if (pessoa.getNome() != null && !pessoa.getNome().isBlank()) {
            pessoaDb.setNome(pessoa.getNome());
        }

        if (pessoa.getEmail() != null && !pessoa.getEmail().isBlank()) {
            pessoaDb.setEmail(pessoa.getEmail());
        }

        return pessoaDb;
    }

}
