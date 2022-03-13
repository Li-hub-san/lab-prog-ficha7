package com.labprog.ficha7.controller;

import com.labprog.ficha7.model.Empresa;
import com.labprog.ficha7.service.ServiceEmpresa;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorEmpresa {
    private final ServiceEmpresa serviceEmpresa;

    public ControladorEmpresa(ServiceEmpresa serviceEmpresa) {
        this.serviceEmpresa = serviceEmpresa;
    }

    @PostMapping("/addEmpresa")
    public Empresa addEmpresa(@RequestBody Empresa empresa) {
        return serviceEmpresa.addEmpresa(empresa);
    }

    @PutMapping("/updateEmpresa")
    public Empresa updateEmpresa(@RequestBody Empresa empresa) throws Exception {
        return serviceEmpresa.updateEmpresa(empresa);
    }

    @DeleteMapping("/deleteEmpresa/{id}")
    public String deleteEmpresa(@PathVariable int id) throws Exception {
        serviceEmpresa.deleteEmpresa(id);
        return "Empresa apagada com sucesso.";
    }

    @GetMapping("/getAllEmpresas")
    public List<Empresa> getEmpresas() {
        return serviceEmpresa.getEmpresas();
    }

    @GetMapping("/getEmpresaById/{id}")
    public Empresa getEmpresa(@PathVariable int id) throws Exception {
        return serviceEmpresa.getEmpresa(id);
    }

}
