package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.model.Pessoa;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceEmpresa {
    private final List<Empresa> empresas;
    private final ServicePessoa servicePessoa;

    public ServiceEmpresa(@Lazy ServicePessoa servicePessoa) {
        this.servicePessoa = servicePessoa;
        this.empresas = new ArrayList<>();
    }

    public Empresa addEmpresa(Empresa empresa) throws Exception {
        if (empresa.getNome() == null || empresa.getMorada().isBlank() || empresa.getMorada() == null) {
            throw new Exception("Campos incompletos.");
        }

        empresas.add(empresa);
        return empresa;
    }

    public Empresa updateEmpresa(Empresa empresa) throws Exception {
        Empresa empresaDb = getEmpresa(empresa.getId());

        if (empresa.getNome() != null && !empresa.getNome().isBlank()) {
            empresaDb.setNome(empresa.getNome());
        } else {
            throw new Exception("Nome nulo ou em branco.");
        }

        if (empresa.getMorada() != null && !empresa.getMorada().isBlank()) {
            empresaDb.setMorada(empresa.getMorada());
        } else {
            throw new Exception("Morada nula ou em branco.");
        }

            return empresaDb;
    }

    public void deleteEmpresa(int id) throws Exception {
        Empresa empresa = getEmpresa(id);

        for (Pessoa pessoa : empresa.getPessoas()) {
            servicePessoa.deletePessoa(pessoa.getId());
        }

        empresas.remove(empresa);
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public Empresa getEmpresa(int id) throws Exception {
        for (Empresa empresa : empresas) {
            if (empresa.getId() == id) {
                return empresa;
            }
        }
        throw new Exception("Empresa com o id " + id + " inexistente.");
    }
}
