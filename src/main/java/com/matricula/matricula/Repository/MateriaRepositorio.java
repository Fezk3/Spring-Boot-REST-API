package com.matricula.matricula.Repository;

import com.matricula.matricula.Entity.Materia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepositorio extends CrudRepository<Materia, Long>{
    List<Materia> findAllByPeriodoId(Long idPeriodo);

}
