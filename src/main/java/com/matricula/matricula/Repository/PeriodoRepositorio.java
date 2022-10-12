package com.matricula.matricula.Repository;

import com.matricula.matricula.Entity.Periodo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoRepositorio extends CrudRepository<Periodo, Long>{

}
