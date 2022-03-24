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
        validateIdNotExists(pessoa);
        validateFields(pessoa);

        Empresa empresaDb = serviceEmpresa.getEmpresa(pessoa.getEmpresa().getId());
        empresaDb.aumentarFuncionariosAtuais();
        pessoa.setEmpresa(empresaDb);

        return pessoaRepository.save(pessoa);
    }

    private void validateIdNotExists(Pessoa pessoa) throws SimpleException {
        if (pessoa.getId() != null) {
            throw new SimpleException(ExceptionCode.ID_NAO_PERMITIDO, "A DB é responsável por gerar o id.");
        }
    }

    private void validateFields(Pessoa pessoa) throws SimpleException {
        validateIdade(pessoa);
        validateNome(pessoa);
        validateEmpresa(pessoa);
    }

    private void validateEmpresa(Pessoa pessoa) throws SimpleException {
        if (pessoa.getEmpresa() == null) {
            throw new SimpleException(ExceptionCode.NAO_ENCONTRADO, "Empresa não encontrada");
        }
    }

    private void validateIdade(Pessoa pessoa) throws SimpleException {
        if (pessoa.getIdade() < 16) {
            throw new SimpleException(ExceptionCode.IDADE_INVALIDA, "Idade < 16");
        }
    }

    private void validateNome(Pessoa pessoa) throws SimpleException {
        if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new SimpleException(ExceptionCode.NOME_INVALIDO, "Nome nulo ou em branco");
        }
    }

    public Pessoa updatePessoa(Pessoa pessoa) throws SimpleException {
        if (pessoa.getId() == null) {
            throw new SimpleException(ExceptionCode.ID_INVALIDO, "Id obrigatório");
        }

        Pessoa pessoaDb = getPessoa(pessoa.getId());

        validateFields(pessoa);

        pessoaDb.setIdade(pessoa.getIdade());
        pessoaDb.setNome(pessoa.getNome());
        pessoaDb.setEmail(pessoa.getEmail());

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
        return pessoaRepository.findAllByOrderByIdDesc();
    }

    public Pessoa getPessoa(Long id) throws SimpleException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return pessoa.get();
        }

        throw new SimpleException(ExceptionCode.NAO_ENCONTRADO, "Pessoa com o id " + id + " inexistente");
    }

}
