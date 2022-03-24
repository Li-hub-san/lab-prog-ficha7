package com.labprog.ficha7.repository;

import com.labprog.ficha7.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    List<Pessoa> findAllByOrderByIdDesc();
}
