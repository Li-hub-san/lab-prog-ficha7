package com.labprog.ficha7.controller;

import com.labprog.ficha7.dto.EmpresaResponse;
import com.labprog.ficha7.dto.EmpresasResponse;
import com.labprog.ficha7.dto.SimpleResponse;
import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.service.ServiceEmpresa;
import com.labprog.ficha7.utils.SimpleException;
import com.labprog.ficha7.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorEmpresa {
    private final ServiceEmpresa serviceEmpresa;

    public ControladorEmpresa(ServiceEmpresa serviceEmpresa) {
        this.serviceEmpresa = serviceEmpresa;
    }

    @PostMapping("/addEmpresa")
    public ResponseEntity<SimpleResponse> addEmpresa(@RequestBody Empresa empresa) {
        try {
            EmpresaResponse er = new EmpresaResponse();
            er.setEmpresa(serviceEmpresa.addEmpresa(empresa));
            er.setMessage("Empresa adicionada com sucesso.");
            return Utils.sucesso(er);
        } catch (SimpleException e) {
            return Utils.erro(e);
        }
    }

    @PutMapping("/updateEmpresa")
    public ResponseEntity<SimpleResponse> updateEmpresa(@RequestBody Empresa empresa) {
        try {
            EmpresaResponse er = new EmpresaResponse();
            er.setEmpresa(serviceEmpresa.updateEmpresa(empresa));
            er.setMessage("Empresa atualizada com sucesso.");
            return Utils.sucesso(er);
        } catch (SimpleException e) {
            return Utils.erro(e);
        }
    }

    @DeleteMapping("/deleteEmpresa/{id}")
    public ResponseEntity<SimpleResponse> deleteEmpresa(@PathVariable Long id) {
        try {
            SimpleResponse sr = new SimpleResponse();
            serviceEmpresa.deleteEmpresa(id);
            sr.setMessage("Empresa eliminada com sucesso.");
            return Utils.sucesso(sr);
        } catch (SimpleException e) {
            return Utils.erro(e);
        }
    }

    @GetMapping("/getAllEmpresas")
    public ResponseEntity<SimpleResponse> getEmpresas() {
        EmpresasResponse er = new EmpresasResponse();
        er.setEmpresas(serviceEmpresa.getEmpresas());
        er.setMessage("Empresas encontradas.");

        return Utils.sucesso(er);
    }

    @GetMapping("/getEmpresaById/{id}")
    public ResponseEntity<SimpleResponse> getEmpresa(@PathVariable Long id) {
        try {
            EmpresaResponse er = new EmpresaResponse();
            er.setEmpresa(serviceEmpresa.getEmpresa(id));
            er.setMessage("Empresa encontrada.");

            return Utils.sucesso(er);
        } catch (SimpleException e) {
            return Utils.erro(e);
        }
    }

}
