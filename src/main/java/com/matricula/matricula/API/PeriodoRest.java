package com.matricula.matricula.API;

import com.matricula.matricula.Entity.Materia;
import com.matricula.matricula.Entity.Periodo;
import com.matricula.matricula.Repository.PeriodoRepositorio;
import com.matricula.matricula.Repository.MateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/periodo")
public class PeriodoRest {

    @Autowired
    private PeriodoRepositorio periodoRepositorio;

    @Autowired
    private MateriaRepositorio materiaRepositorio;

    // metodo que checkea si existe una materia asociada a un periodo
    public boolean checkMateriaPeriodo(Long idPeriodo) {
        List<Materia> list = new ArrayList<Materia>();
        materiaRepositorio.findAll().forEach(e -> list.add(e));
        for (Materia m : list) {
            if (m.getPeriodo().getId() == idPeriodo) {
                return true;
            }
        }
        return false;
    }

    //Metodo get para el read
    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Periodo>> findAll() {
        List<Periodo> list = new ArrayList<Periodo>();
        periodoRepositorio.findAll().forEach(e -> list.add(e));
        return ResponseEntity.ok(list);
    }

    //Metodo get para el read de un periodo
    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Periodo> findById(@PathVariable("id") long id) {
        Optional<Periodo> periodo = periodoRepositorio.findById(id);
        if (!periodo.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(periodo.get());
    }

    // Metodo post para el create
    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity create(@RequestBody Periodo periodo) {
        return ResponseEntity.ok(periodoRepositorio.save(periodo));
    }

    //Metodo put para el update
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Periodo> update(@PathVariable("id") Long id, @RequestBody Periodo periodo) {
        if (!periodoRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            periodo.setId(id);
            return ResponseEntity.ok(periodoRepositorio.save(periodo));
        }
    }

    //Metodo delete para el delete
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable("id") Long id) {

        // si existe una materia asociada al periodo, no se puede eliminar
        if (!periodoRepositorio.findById(id).isPresent() || checkMateriaPeriodo(id)) {
            return ResponseEntity.notFound().build();
        } else {
            periodoRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

}
