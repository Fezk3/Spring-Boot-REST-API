package com.matricula.matricula.API;

import com.matricula.matricula.Entity.Matricula;
import com.matricula.matricula.Repository.MatriculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/matricula")
public class MatriculaRest {

    @Autowired
    private MatriculaRepositorio matriculaRepositorio;

    //Metodo get para el read
    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Matricula>> findAll() {
        List<Matricula> list = new ArrayList<Matricula>();
        matriculaRepositorio.findAll().forEach(e -> list.add(e));
        return ResponseEntity.ok(list);
    }

    //Metodo get para el read de una matricula
    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Matricula> findById(@PathVariable("id") long id) {
        Optional<Matricula> matricula = matriculaRepositorio.findById(id);
        if (!matricula.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(matricula.get());
    }

    // Metodo post para el create
    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity create(@RequestBody Matricula matricula) {

        // validacion de los cupos de la materia que se quiere matricular
        int cupos = matricula.getMateria().getCupos();

        if(cupos > 0){
            matricula.getMateria().setCupos(cupos - 1);
            return ResponseEntity.ok(matriculaRepositorio.save(matricula));
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    //Metodo put para el update
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Matricula> update(@PathVariable("id") Long id, @RequestBody Matricula matricula) {
        if (!matriculaRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            matricula.setId(id);
            return ResponseEntity.ok(matriculaRepositorio.save(matricula));
        }
    }

    //Metodo delete para el delete
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (!matriculaRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            matriculaRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

}
