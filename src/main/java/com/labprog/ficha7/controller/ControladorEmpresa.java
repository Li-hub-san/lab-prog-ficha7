package com.labprog.ficha7.controller;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.service.ServiceEmpresa;
import com.labprog.ficha7.dto.SimpleResponse;
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
            serviceEmpresa.addEmpresa(empresa);
            return Utils.sucesso("Empresa adicionada com sucesso.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

    @PutMapping("/updateEmpresa")
    public ResponseEntity<SimpleResponse> updateEmpresa(@RequestBody Empresa empresa) {
        try {
            serviceEmpresa.updateEmpresa(empresa);
            return Utils.sucesso("Empresa atualizada com sucesso.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

    @DeleteMapping("/deleteEmpresa/{id}")
    public ResponseEntity<SimpleResponse> deleteEmpresa(@PathVariable int id) {
        try {
            serviceEmpresa.deleteEmpresa(id);
            return Utils.sucesso("Empresa apagada com sucesso.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

    @GetMapping("/getAllEmpresas")
    public ResponseEntity<SimpleResponse> getEmpresas() {
        try {
            serviceEmpresa.getEmpresas();
            return Utils.sucesso("Empresas encontradas.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

    @GetMapping("/getEmpresaById/{id}")
    public ResponseEntity<SimpleResponse> getEmpresa(@PathVariable int id) {
        try {
            serviceEmpresa.getEmpresa(id);
            return Utils.sucesso("Empresa encontrada.");
        } catch (Exception e) {
            return Utils.erro(e);
        }
    }

}
