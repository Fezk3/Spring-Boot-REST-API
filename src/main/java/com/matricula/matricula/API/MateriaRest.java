package com.matricula.matricula.API;

import com.matricula.matricula.Entity.Materia;
import com.matricula.matricula.Repository.MateriaRepositorio;
import com.matricula.matricula.Entity.Periodo;
import com.matricula.matricula.Repository.PeriodoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/materia")
public class MateriaRest {

    @Autowired
    private MateriaRepositorio materiaRepositorio;

    @Autowired
    private PeriodoRepositorio periodoRepositorio;

    //Metodo get para el read
    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Materia>> findAll() {
        List<Materia> list = new ArrayList<Materia>();
        materiaRepositorio.findAll().forEach(e -> list.add(e));
        return ResponseEntity.ok(list);
    }

    //Metodo get para el read de una materia
    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Materia> findById(@PathVariable("id") long id) {
        Optional<Materia> materia = materiaRepositorio.findById(id);
        if (!materia.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(materia.get());
    }

    // Metodo post para el create
    @PostMapping("/{idPeriodo}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity create(@RequestBody Materia materia, @PathVariable("idPeriodo") Long idPeriodo) {
        Periodo p = periodoRepositorio.findById(idPeriodo).orElseThrow(() -> new RuntimeException("Error: Periodo no encontrado."));
        materia.setPeriodo(p);
        return ResponseEntity.ok(materiaRepositorio.save(materia));
    }

    //Metodo put para el update
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Materia> update(@PathVariable("id") Long id, @RequestBody Materia materia) {
        if (!materiaRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            materia.setId(id);
            return ResponseEntity.ok(materiaRepositorio.save(materia));
        }
    }

    //Metodo delete para el delete
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (!materiaRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            materiaRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
