package com.matricula.matricula.API;

import com.matricula.matricula.Repository.LogRepositorio;
import com.matricula.matricula.Entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/log")
public class LogRest {

    @Autowired
    private LogRepositorio logRepositorio;

    //Metodo get para el read de los logs
    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Log>> findAll() {
        List<Log> list = new ArrayList<Log>();
        logRepositorio.findAll().forEach(e -> list.add(e));
        return ResponseEntity.ok(list);
    }
}
