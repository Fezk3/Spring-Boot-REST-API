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
    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity create(@RequestBody MateriaPeriodo periodoMateria) {
        Materia m = new Materia();
        m.setDescripcion(periodoMateria.getDescripcion());
        m.setCupos(periodoMateria.getCupos());
        Periodo p = periodoRepositorio.findById(periodoMateria.getIdPeriodo()).get();
        m.setPeriodo(p);
        return ResponseEntity.ok(materiaRepositorio.save(m));
    }

    //Metodo put para el update
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Materia> update(@PathVariable("id") Long id, @RequestBody MateriaPeriodo periodoMateria) {
        if (!materiaRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Materia m = new Materia();
            m.setId(id);
            m.setDescripcion(periodoMateria.getDescripcion());
            m.setCupos(periodoMateria.getCupos());
            Periodo p = periodoRepositorio.findById(periodoMateria.getIdPeriodo()).get();
            m.setPeriodo(p);
            return ResponseEntity.ok(materiaRepositorio.save(m));
        }
    }

    //Metodo delete para el delete
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (!materiaRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            // desvinculo la materia del periodo
            Materia m = materiaRepositorio.findById(id).get();
            m.setPeriodo(null);
            materiaRepositorio.save(m);
            System.out.println(m.getPeriodo());
            // eliminino la materia
            materiaRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping(path = "/periodo/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Materia>> findAllByPeriodoId(@PathVariable("id") long idPeriodo) {
        List<Materia> list = new ArrayList<Materia>();
        materiaRepositorio.findAllByPeriodoId(idPeriodo).forEach(e -> list.add(e));
        return ResponseEntity.ok(list);
    }

    //metodo delete que verifica si la materia tiene matriculas
    @DeleteMapping("/verificar/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity deleteMateria(@PathVariable("id") Long id) {
        if (!materiaRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            if (materiaRepositorio.findById(id).get().getMatriculas().isEmpty()) {
                materiaRepositorio.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }



}

// clase para asignar un periodo a una materia en el post
class MateriaPeriodo {

    public String descripcion;
    public int cupos;

    // id del periodo que ya existe
    public Long idPeriodo;

    public MateriaPeriodo(){

    }

    // getters y setters
    public String getDescripcion() {

        return descripcion;

    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;

    }

    public int getCupos() {

        return cupos;

    }

    public void setCupos(int cupos) {

        this.cupos = cupos;

    }

    public Long getIdPeriodo() {

        return idPeriodo;

    }

    public void setIdPeriodo(Long idPeriodo) {

        this.idPeriodo = idPeriodo;

    }
}
