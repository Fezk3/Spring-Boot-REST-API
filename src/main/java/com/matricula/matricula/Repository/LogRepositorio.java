package com.matricula.matricula.Repository;

import com.matricula.matricula.Entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepositorio extends CrudRepository<Log, Long>{

}
