package com.labprog.ficha7.dto;

import com.labprog.ficha7.model.Empresa;

import java.util.List;

public class EmpresasResponse extends SimpleResponse {
    private List<Empresa> empresas;

    public EmpresasResponse() {
        this.status = true;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

}
