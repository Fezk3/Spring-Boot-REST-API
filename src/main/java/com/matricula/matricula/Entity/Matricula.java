package com.matricula.matricula.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Matricula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relacion con periodo
    @OneToOne
    @JoinColumn(name = "id_Periodo", referencedColumnName = "id")
    private Periodo periodo;

    // relacion con materia
    @OneToOne
    @JoinColumn(name = "id_Materia", referencedColumnName = "id")
    private Materia materia;

    // relacion con persona
    @OneToOne
    @JoinColumn(name = "id_Persona", referencedColumnName = "id")
    private Persona persona;

    // constructor vacio
    public Matricula() {
    }

    // setters y getters
    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public Periodo getPeriodo() {

        return periodo;

    }

    public void setPeriodo(Periodo periodo) {

        this.periodo = periodo;

    }

    public Materia getMateria() {

        return materia;

    }

    public void setMateria(Materia materia) {

        this.materia = materia;

    }

    public Persona getPersona() {

        return persona;

    }

    public void setPersona(Persona persona) {

        this.persona = persona;

    }
}
