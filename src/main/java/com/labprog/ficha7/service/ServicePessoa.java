package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePessoa {
    public List<Pessoa> pessoas = new ArrayList<>();

    public Pessoa addPessoa(Pessoa pessoa) {
        if (pessoa.getIdade() > 0 && pessoa.getNome() != null && !pessoa.getNome().isBlank()) {
            pessoas.add(pessoa);
        }

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
        throw new Exception("Id inexistente");

    }

    public boolean deletePessoa(int id) {
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa pessoa = pessoas.get(i);
            if (pessoa.getId() == id) {
                pessoas.remove(pessoa);
                return true;
            }
        }
        return false;
    }

    public Pessoa updatePessoa(Pessoa pessoa) throws Exception {
        for (Pessoa pessoaDb : pessoas) {
            if (pessoa.getId() == pessoaDb.getId()) {
                if (pessoa.getIdade() > 0) {
                    pessoaDb.setIdade(pessoa.getIdade());
                }
                if (pessoa.getNome() != null && !pessoa.getNome().isBlank()) {
                    pessoaDb.setNome(pessoa.getNome());
                }
                if (pessoa.getEmail() != null && !pessoa.getEmail().isBlank()) {
                    pessoaDb.setEmail(pessoa.getEmail());
                }
//                if (pessoa.getEmpresa() != null && pessoa.getEmpresa().getId() != null) {
//                    pessoaDb.setEmpresa(pessoa.getEmpresa());
//                }
                return pessoaDb;
            }
        }

        throw new Exception("Pessoa inexistente");
    }


}
