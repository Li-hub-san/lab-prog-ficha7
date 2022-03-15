package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.repository.EmpresaRepository;
import com.labprog.ficha7.utils.ExceptionCode;
import com.labprog.ficha7.utils.SimpleException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmpresa {
    private final EmpresaRepository empresaRepository;

    public ServiceEmpresa(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
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
        if (empresa.getId() == null) {
            throw new SimpleException(ExceptionCode.ID_INVALIDO, "Id obrigatório");
        }

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

        return empresaRepository.save(empresaDb);
    }

    public void deleteEmpresa(Long id) throws SimpleException {
        Empresa empresa = getEmpresa(id);
        empresaRepository.delete(empresa);
    }

    public List<Empresa> getEmpresas() {
        return (List<Empresa>) empresaRepository.findAll();
    }

    public Empresa getEmpresa(Long id) throws SimpleException {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()) {
            return empresa.get();
        }

        throw new SimpleException(ExceptionCode.NAO_ENCONTRADO, "Empresa com o id " + id + " inexistente.");
    }
}
