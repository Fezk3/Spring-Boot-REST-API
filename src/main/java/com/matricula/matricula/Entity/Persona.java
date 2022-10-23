package com.matricula.matricula.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.matricula.matricula.Entity.Materia;

@Entity
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacion;
    private String nombre;

    // constructor vacio
    public Persona() {
    }

    // setters y getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Object> getMatriculas() {
        Collection<Object> matriculas = null;
        return matriculas;
    }
}
