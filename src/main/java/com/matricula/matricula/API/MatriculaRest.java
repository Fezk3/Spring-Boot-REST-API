package com.matricula.matricula.API;

import com.matricula.matricula.Entity.Matricula;
import com.matricula.matricula.Entity.Materia;
import com.matricula.matricula.Entity.Periodo;
import com.matricula.matricula.Entity.Persona;
import com.matricula.matricula.Repository.MateriaRepositorio;
import com.matricula.matricula.Repository.MatriculaRepositorio;
import com.matricula.matricula.Repository.PeriodoRepositorio;
import com.matricula.matricula.Repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
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

    @Autowired
    private PeriodoRepositorio periodoRepositorio;

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Autowired
    private MateriaRepositorio materiaRepositorio;

    // metodo que checkea si una persona ya esta matriculada en una materia en un periodo determinado
    public boolean checkMatriculaPersona(Long idPersona, Long idMateria, Long idPeriodo) {
        List<Matricula> list = new ArrayList<Matricula>();
        matriculaRepositorio.findAll().forEach(e -> list.add(e));
        for (Matricula m : list) {
            if (m.getPersona().getId() == idPersona && m.getMateria().getId() == idMateria && m.getPeriodo().getId() == idPeriodo) {
                return true;
            }
        }
        return false;
    }

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
    public ResponseEntity create(@RequestBody MatriculaPPM matriculaPPM) {

        Materia m = materiaRepositorio.findById(matriculaPPM.getIdMateria()).get();

        int cupos = m.getCupos();

        // solo hace matricula si hay cupos y si la persona no esta matriculada en la materia previamente
        if(cupos > 0 && !checkMatriculaPersona(matriculaPPM.getIdPersona(), matriculaPPM.getIdMateria(), matriculaPPM.getIdPeriodo())) {

            Persona p = personaRepositorio.findById(matriculaPPM.getIdPersona()).get();
            Periodo pe = periodoRepositorio.findById(matriculaPPM.getIdPeriodo()).get();
            Matricula matricula = new Matricula();
            matricula.setMateria(m);
            matricula.setPersona(p);
            matricula.setPeriodo(pe);
            // decremento de los cupos de la materia matriculada
            m.setCupos(m.getCupos() - 1);
            materiaRepositorio.save(m);

            return ResponseEntity.ok(matriculaRepositorio.save(matricula));

        }else{

            return ResponseEntity.notFound().build();
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El curso" + m.getDescripcion()+" ya no cuenta con cupos disponibles");

        }

    }

    //Metodo put para el update
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Matricula> update(@PathVariable("id") Long id, @RequestBody MatriculaPPM matriculaPPM) {

        if (!matriculaRepositorio.findById(id).isPresent()) {

            return ResponseEntity.notFound().build();

        } else {

            // se obtiene la matricula a actualizar
            Matricula matricula = matriculaRepositorio.findById(id).get();
            Materia anterior = matricula.getMateria();
            Materia nueva = materiaRepositorio.findById(matriculaPPM.getIdMateria()).get();
            // check si nueva tiene cupos disponibles y no esta matriculada previamente en la materia nueva
            if (nueva.getCupos() == 0 && !checkMatriculaPersona(matriculaPPM.getIdPersona(), matriculaPPM.getIdMateria(), matriculaPPM.getIdPeriodo())) {
                return ResponseEntity.notFound().build();
            }
            // sumando un cupo de la materia que se desmatricula
            anterior.setCupos(anterior.getCupos() + 1);
            materiaRepositorio.save(anterior);
            // decrementando un cupo de la materia a matricular
            nueva.setCupos(nueva.getCupos() - 1);
            materiaRepositorio.save(nueva);
            // asiganacion de los nuevos valores de la matricula
            matricula.setId(id);
            matricula.setMateria(nueva);
            matricula.setPersona(personaRepositorio.findById(matriculaPPM.getIdPersona()).get());
            matricula.setPeriodo(periodoRepositorio.findById(matriculaPPM.getIdPeriodo()).get());
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

            Matricula matricula = matriculaRepositorio.findById(id).get();
            Materia m = matricula.getMateria();
            // incremento de los cupos de la materia matriculada
            m.setCupos(m.getCupos() + 1);
            materiaRepositorio.save(m);
            // desvinculo la matricula de la materia, la persona y el periodo
            matricula.setMateria(null);
            matricula.setPersona(null);
            matricula.setPeriodo(null);
           // matriculaRepositorio.save(matricula);
            // elimino la matricula
            matriculaRepositorio.deleteById(id);
            return ResponseEntity.ok().build();

        }

    }

}

// clase para asignar los datos de la matricula
class MatriculaPPM {

    private long idMateria;
    private long idPersona;
    private long idPeriodo;

    public MatriculaPPM() {
    }

    // getters y setters
    public long getIdMateria() {

        return idMateria;

    }

    public void setIdMateria(long idMateria) {

        this.idMateria = idMateria;

    }

    public long getIdPersona() {

        return idPersona;

    }

    public void setIdPersona(long idPersona) {

        this.idPersona = idPersona;

    }

    public long getIdPeriodo() {

        return idPeriodo;

    }

    public void setIdPeriodo(long idPeriodo) {

        this.idPeriodo = idPeriodo;

    }
}