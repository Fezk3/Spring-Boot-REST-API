package com.matricula.matricula.Repository;

import com.matricula.matricula.Entity.Matricula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepositorio extends CrudRepository<Matricula, Long>{

}
