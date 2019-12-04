/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nightm4re
 */


@Entity
@Table(name="SOSPECHOSOS")
public class Sospechoso implements Serializable {
    
    @Id
    @Column(name="SOSP_ID")
    private long id;
    @Column(name="NOMBRE")
    private String nombre;
    @Column(name="NACIONALIDAD")
    private String nacionalidad;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Antecedentes> antecedentes;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Correo> correos;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<NumeroTelefono> telefonos;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Direccion> direcciones;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Matricula> matriculas;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DatosExtra> datosextra;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Foto> fotos;

   public Sospechoso(){}

    public Sospechoso(String nombre, String nacionalidad, List<Antecedentes> antecedentes, List<Correo> correos, List<DatosExtra> datosextra, List<Direccion> direcciones, List<Foto> fotos, List<Matricula> matriculas,  List<NumeroTelefono> telefonos  ) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.antecedentes = antecedentes;
        this.correos = correos;
        this.fotos = fotos;
        this.telefonos = telefonos;
        this.direcciones = direcciones;
        this.matriculas = matriculas;
        this.datosextra = datosextra;
    }   

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setAntecedentes(List<Antecedentes> antecedentes) {
        this.antecedentes = antecedentes;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
    }

    public void setTelefonos(List<NumeroTelefono> telefonos) {
        this.telefonos = telefonos;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public void setDatosextra(List<DatosExtra> datosextra) {
        this.datosextra = datosextra;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
    
    

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public List<Antecedentes> getAntecedentes() {
        return antecedentes;
    }

    public List<Correo> getCorreos() {
        return correos;
    }

    public List<NumeroTelefono> getTelefonos() {
        return telefonos;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public List<DatosExtra> getDatosextra() {
        return datosextra;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    
    
}
