package com.labprog.ficha7.controller;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.service.ServiceEmpresa;
import com.labprog.ficha7.sto.SimpleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorEmpresa extends ControladorBasico {
    private final ServiceEmpresa serviceEmpresa;

    public ControladorEmpresa(ServiceEmpresa serviceEmpresa) {
        this.serviceEmpresa = serviceEmpresa;
    }

    @PostMapping("/addEmpresa")
    public ResponseEntity<SimpleResponse> addEmpresa(@RequestBody Empresa empresa) {
        try {
            return sucesso(serviceEmpresa.addEmpresa(empresa), "Empresa adicionada com sucesso.");
        } catch (Exception e) {
            return erro(e);
        }
    }

    @PutMapping("/updateEmpresa")
    public ResponseEntity<SimpleResponse> updateEmpresa(@RequestBody Empresa empresa) {
        try {
            return sucesso(serviceEmpresa.updateEmpresa(empresa), "Empresa atualizada com sucesso.");
        } catch (Exception e) {
            return erro(e);
        }
    }

    @DeleteMapping("/deleteEmpresa/{id}")
    public ResponseEntity<SimpleResponse> deleteEmpresa(@PathVariable int id) {
        try {
            serviceEmpresa.deleteEmpresa(id);
            return sucesso(null, "Empresa apagada com sucesso.");
        } catch (Exception e) {
            return erro(e);
        }
    }

    @GetMapping("/getAllEmpresas")
    public ResponseEntity<SimpleResponse> getEmpresas() {
        try {
            return sucesso(serviceEmpresa.getEmpresas(), "Empresas encontradas.");
        } catch (Exception e) {
            return erro(e);
        }
    }

    @GetMapping("/getEmpresaById/{id}")
    public ResponseEntity<SimpleResponse> getEmpresa(@PathVariable int id) throws Exception {
        try {
            return sucesso(serviceEmpresa.getEmpresa(id), "Empresa encontrada.");
        } catch (Exception e) {
            return erro(e);
        }
    }

}
