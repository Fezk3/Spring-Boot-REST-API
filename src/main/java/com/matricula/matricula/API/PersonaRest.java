package com.matricula.matricula.API;

import com.matricula.matricula.Entity.Persona;
import com.matricula.matricula.Repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/persona")
public class PersonaRest {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    // metodo que checkea si una persona tiene una matricula || quizas se debe poner en cascada :/
    public boolean checkMatriculaPersona(Long idPersona) {
        List<Persona> list = new ArrayList<Persona>();
        personaRepositorio.findAll().forEach(e -> list.add(e));
        for (Persona p : list) {
            if (p.getId() == idPersona) {
                return true;
            }
        }
        return false;
    }

    //Metodo get para el read
    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> list = new ArrayList<Persona>();
        personaRepositorio.findAll().forEach(e -> list.add(e));
        return ResponseEntity.ok(list);
    }

    //Metodo get para el read de una persona
    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Persona> findById(@PathVariable("id") long id) {
        Optional<Persona> persona = personaRepositorio.findById(id);
        if (!persona.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(persona.get());
    }

    // Metodo post para el create
    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity create(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaRepositorio.save(persona));
    }

    //Metodo put para el update
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Persona> update(@PathVariable("id") Long id, @RequestBody Persona persona) {
        if (!personaRepositorio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            persona.setId(id);
            return ResponseEntity.ok(personaRepositorio.save(persona));
        }
    }

    //Metodo delete para el delete
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Persona> delete(@PathVariable("id") long id) {

        // no elimina la persona si tiene una matricula
        if (!personaRepositorio.findById(id).isPresent() || checkMatriculaPersona(id)) {
            return ResponseEntity.notFound().build();
        }
        personaRepositorio.deleteById(id);
        return ResponseEntity.ok().build();
    }
}