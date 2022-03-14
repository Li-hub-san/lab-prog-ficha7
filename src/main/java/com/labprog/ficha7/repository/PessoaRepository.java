package com.labprog.ficha7.repository;

import com.labprog.ficha7.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
}
