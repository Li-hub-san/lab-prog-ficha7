package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.model.Pessoa;
import com.labprog.ficha7.repository.EmpresaRepository;
import com.labprog.ficha7.repository.PessoaRepository;
import com.labprog.ficha7.utils.ExceptionCode;
import com.labprog.ficha7.utils.SimpleException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePessoa {
    private final ServiceEmpresa serviceEmpresa;
    private final PessoaRepository pessoaRepository;
    private final EmpresaRepository empresaRepository;

    public ServicePessoa(ServiceEmpresa serviceEmpresa,
                         PessoaRepository pessoaRepository,
                         EmpresaRepository empresaRepository) {
        this.serviceEmpresa = serviceEmpresa;
        this.pessoaRepository = pessoaRepository;
        this.empresaRepository = empresaRepository;
    }

    public Pessoa addPessoa(Pessoa pessoa) throws SimpleException {
        if (pessoa.getId() != null) {
            throw new SimpleException(ExceptionCode.ID_NAO_PERMITIDO, "A DB é responsável por gerar o id.");
        }

        if (pessoa.getIdade() < 16 || pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new SimpleException(ExceptionCode.CAMPOS_INCOMPLETOS, "Campos incompletos.");
        }

        if (pessoa.getEmpresa() == null) {
            throw new SimpleException(ExceptionCode.NAO_ENCONTRADO, "Empresa não encontrada");
        }

        Empresa empresaDb = serviceEmpresa.getEmpresa(pessoa.getEmpresa().getId());
        empresaDb.aumentarFuncionariosAtuais();
        pessoa.setEmpresa(empresaDb);

        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Pessoa pessoa) throws SimpleException {
        if (pessoa.getId() == null) {
            throw new SimpleException(ExceptionCode.ID_INVALIDO, "Id obrigatório");
        }

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
            throw new SimpleException(ExceptionCode.EMAIL_INVALIDO, "Email nulo ou em branco");
        }

        return pessoaRepository.save(pessoaDb);
    }

    public void deletePessoa(Long id) throws SimpleException {
        Pessoa pessoa = getPessoa(id);
        Empresa empresa = serviceEmpresa.getEmpresa(pessoa.getEmpresa().getId());
        pessoaRepository.delete(pessoa);

        empresa.reduzirFuncionariosAtuais();
        empresaRepository.save(empresa);
    }

    public List<Pessoa> getPessoas() {
        return (List<Pessoa>) pessoaRepository.findAll();
    }

    public Pessoa getPessoa(Long id) throws SimpleException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return pessoa.get();
        }

        throw new SimpleException(ExceptionCode.NAO_ENCONTRADO, "Pessoa com o id " + id + " inexistente");
    }

}
