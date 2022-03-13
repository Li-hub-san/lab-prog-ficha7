package com.labprog.ficha7.service;

import com.labprog.ficha7.model.Empresa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceEmpresa {
    private final List<Empresa> empresas;
    private final ServicePessoa servicePessoa;

    public ServiceEmpresa(ServicePessoa servicePessoa) {
        this.servicePessoa = servicePessoa;
        this.empresas = new ArrayList<>();
    }

    public Empresa addEmpresa(Empresa empresa) {
        empresas.add(empresa);
        return empresa;
    }

    public Empresa updateEmpresa(Empresa empresa) throws Exception {
        for (Empresa empresaDb : empresas) {
            if (empresaDb.getId() == empresa.getId()) {
                if (empresa.getNome() != null && !empresa.getNome().isBlank()) {
                    empresaDb.setNome(empresa.getNome());
                }
                if (empresa.getMorada() != null && !empresa.getMorada().isBlank()) {
                    empresaDb.setMorada(empresa.getMorada());
                }
                return empresa;
            }
        }

        throw new Exception("Empresa Inexistente");
    }

    public boolean deleteEmpresa(int id) {
        for (Empresa empresa : empresas) {
            if (empresa.getId() == id) {
                empresa.getPessoas().forEach(pessoa -> servicePessoa.deletePessoa(pessoa.getId()));
                empresas.remove(empresa);
                return true;
            }
        }
        return false;
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
        throw new Exception("Empresa com id " + id + " inexistente.");
    }
}
