package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.model.Pessoa;
import com.labprog.ficha7.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePessoa {
    public List<Pessoa> pessoas = new ArrayList<>();
    private final ServiceEmpresa serviceEmpresa;

    private PessoaRepository pessoaRepository;

    public ServicePessoa(ServiceEmpresa serviceEmpresa, PessoaRepository pessoaRepository) {
        this.serviceEmpresa = serviceEmpresa;
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa addPessoa(Pessoa pessoa) throws Exception {
        if (pessoa.getIdade() < 16 || pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new Exception("Campos incompletos.");
        }

        // validação se a empresa existe ou não está feita dentro de getEmpresa -> menos uma validação neste método.
        Empresa empresa = serviceEmpresa.getEmpresa(pessoa.getEmpresaId());
        empresa.contratar(pessoa);

        pessoaRepository.save(pessoa);
//        pessoas.add(pessoa);

        return pessoa;
    }

    public Pessoa updatePessoa(Pessoa pessoa) throws Exception {
        Pessoa pessoaDb = getPessoa(pessoa.getId());

        if (pessoa.getIdade() >= 16) {
            pessoaDb.setIdade(pessoa.getIdade());
        } else {
            throw new Exception("Idade < 16.");
        }

        if (pessoa.getNome() != null && !pessoa.getNome().isBlank()) {
            pessoaDb.setNome(pessoa.getNome());
        } else {
            throw new Exception("Nome nulo ou em branco.");
        }

        if (pessoa.getEmail() != null && !pessoa.getEmail().isBlank()) {
            pessoaDb.setEmail(pessoa.getEmail());
        } else {
            throw new Exception("Email nulo ou em branco.");
        }

        return pessoaDb;
    }

    public void deletePessoa(int id) throws Exception {
        Pessoa pessoa = getPessoa(id);
        pessoas.remove(pessoa);
        Empresa empresa = serviceEmpresa.getEmpresa(pessoa.getEmpresaId());
        empresa.cessarContrato(pessoa);
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

}
