package com.matricula.matricula.Repository;

import com.matricula.matricula.Entity.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends CrudRepository<Persona, Long>{

}
