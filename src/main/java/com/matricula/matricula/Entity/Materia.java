package com.matricula.matricula.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Materia implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private int cupos;

    @Column(name = "id_periodo")
    private long id_periodo;

    // relacion con la tabla periodo
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_periodo", referencedColumnName = "id", insertable = false, updatable = false)
    private Periodo periodo;

    // constructor vacio
    public Materia() {
    }

    // setters y getters


    public long getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(long id_periodo) {
        this.id_periodo = id_periodo;
    }

    public Periodo getPeriodo() {

        return periodo;

    }

    public void setPeriodo(Periodo periodo) {

        this.periodo = periodo;

    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;

    }

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
}
