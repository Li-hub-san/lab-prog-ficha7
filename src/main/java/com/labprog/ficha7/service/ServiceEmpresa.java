package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.model.Pessoa;
import com.labprog.ficha7.repository.EmpresaRepository;
import com.labprog.ficha7.repository.PessoaRepository;
import com.labprog.ficha7.utils.ExceptionCode;
import com.labprog.ficha7.utils.SimpleException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ServiceEmpresa {
    private final List<Empresa> empresas;
    private final ServicePessoa servicePessoa;
    private final EmpresaRepository empresaRepository;
    private final PessoaRepository pessoaRepository;
    public ServiceEmpresa(@Lazy ServicePessoa servicePessoa,
                          EmpresaRepository empresaRepository,
                          PessoaRepository pessoaRepository) {
        this.servicePessoa = servicePessoa;
        this.empresaRepository = empresaRepository;
        this.pessoaRepository = pessoaRepository;
        this.empresas = new ArrayList<>();
    }

    public Empresa addEmpresa(Empresa empresa) throws SimpleException {
        // por defeito o id tem que ser verificado se vai a null -> se levar id, é feito Update do id já presente na DB.
        if (empresa.getId() != null) {
            throw new SimpleException(ExceptionCode.ID_NAO_PERMITIDO, "A DB é responsável por gerar o id.");
        }

        if (empresa.getNome() == null || empresa.getMorada().isBlank() || empresa.getMorada() == null) {
            throw new SimpleException(ExceptionCode.CAMPOS_INCOMPLETOS, "Campos incompletos.");
        }

        return empresaRepository.save(empresa);
    }

    public Empresa updateEmpresa(Empresa empresa) throws SimpleException {
        Empresa empresaDb = getEmpresa(empresa.getId());

        if (empresa.getNome() != null && !empresa.getNome().isBlank()) {
            empresaDb.setNome(empresa.getNome());
        } else {
            throw new SimpleException(ExceptionCode.NOME_INVALIDO, "Nome nulo ou em branco.");
        }

        if (empresa.getMorada() != null && !empresa.getMorada().isBlank()) {
            empresaDb.setMorada(empresa.getMorada());
        } else {
            throw new SimpleException(ExceptionCode.MORADA_INVALIDA, "Morada nula ou em branco.");
        }

        return empresaDb;
    }

    public void deleteEmpresa(Long id) throws SimpleException {
        Empresa empresa = getEmpresa(id);

        for (Pessoa pessoa : empresa.getPessoas()) {
            servicePessoa.deletePessoa(pessoa.getId());
        }

        empresas.remove(empresa);
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public Empresa getEmpresa(Long id) throws SimpleException {
        for (Empresa empresa : empresas) {
            if (Objects.equals(empresa.getId(), id)) {
                return empresa;
            }
        }
        throw new SimpleException(ExceptionCode.NAO_ENCONTRADO, "Empresa com o id " + id + " inexistente.");
    }
}
