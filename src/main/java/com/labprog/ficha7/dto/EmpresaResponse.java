package com.labprog.ficha7.dto;

import com.labprog.ficha7.model.Empresa;

public class EmpresaResponse extends SimpleResponse {
    private Empresa empresa;

    public EmpresaResponse() {
        this.status = true;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
