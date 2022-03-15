package com.labprog.ficha7.repository;

import com.labprog.ficha7.model.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
}
