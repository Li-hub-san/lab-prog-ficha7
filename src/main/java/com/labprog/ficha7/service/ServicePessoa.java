package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.model.Pessoa;
import com.labprog.ficha7.repository.PessoaRepository;
import com.labprog.ficha7.utils.ExceptionCode;
import com.labprog.ficha7.utils.SimpleException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePessoa {
    public List<Pessoa> pessoas = new ArrayList<>();
    private final ServiceEmpresa serviceEmpresa;
    private final PessoaRepository pessoaRepository;

    public ServicePessoa(ServiceEmpresa serviceEmpresa,
                         PessoaRepository pessoaRepository) {
        this.serviceEmpresa = serviceEmpresa;
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa addPessoa(Pessoa pessoa) throws SimpleException {
        if (pessoa.getId() != null) {
            throw new SimpleException(ExceptionCode.ID_NAO_PERMITIDO, "A DB é responsável por gerar o id.");
        }

        if (pessoa.getIdade() < 16 || pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new SimpleException(ExceptionCode.CAMPOS_INCOMPLETOS, "Campos incompletos.");
        }

        // validação se a empresa existe ou não está feita dentro de getEmpresa -> menos uma validação neste método.
        Empresa empresa = serviceEmpresa.getEmpresa(pessoa.getEmpresaId());
        empresa.contratar(pessoa);

        pessoaRepository.save(pessoa);
//        pessoas.add(pessoa);

        return pessoa;
    }

    public Pessoa updatePessoa(Pessoa pessoa) throws SimpleException {
        Pessoa pessoaDb = getPessoa(pessoa.getId());

        if (pessoa.getIdade() >= 16) {
            pessoaDb.setIdade(pessoa.getIdade());
        } else {
            throw new SimpleException(ExceptionCode.IDADE_INVALIDA, "Idade < 16");
        }

        if (pessoa.getNome() != null && !pessoa.getNome().isBlank()) {
            pessoaDb.setNome(pessoa.getNome());
        } else {
            throw new SimpleException(ExceptionCode.NOME_INVALIDO, "Nome nulo ou em branco");
        }

        if (pessoa.getEmail() != null && !pessoa.getEmail().isBlank()) {
            pessoaDb.setEmail(pessoa.getEmail());
        } else {
            throw new SimpleException(ExceptionCode.EMAIL_INVALIDO, "Email nulo ou em branco.");
        }

        return pessoaDb;
    }

    public void deletePessoa(Long id) throws SimpleException {
        Pessoa pessoa = getPessoa(id);
        pessoas.remove(pessoa);
        Empresa empresa = serviceEmpresa.getEmpresa(pessoa.getEmpresaId());
        empresa.cessarContrato(pessoa);
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Pessoa getPessoa(Long id) throws SimpleException {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId().equals(id)) {
                return pessoa;
            }
        }
        throw new SimpleException(ExceptionCode.NAO_ENCONTRADO, "Pessoa com o id " + id + " inexistente.");
    }

}
