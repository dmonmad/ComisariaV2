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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nightm4re
 */


@Entity
@Table(name="SOSPECHOSOS")
public class SospechosoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SOSP_ID", updatable = false, nullable = false)
    private long id;
    @Column(name="NOMBRE")
    private String nombre;
    @Column(name="NACIONALIDAD")
    private String nacionalidad;
    @Column(name="DNI")
    private String dni;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AntecedentesEntity> antecedentes;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CorreoEntity> correos;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<NumeroTelefonoEntity> telefonos;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DireccionEntity> direcciones;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MatriculaEntity> matriculas;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DatosExtraEntity> datosextra;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FotoEntity> fotos;

   public SospechosoEntity(){}

    public SospechosoEntity(String nombre, String nacionalidad, String dni, List<AntecedentesEntity> antecedentes, List<CorreoEntity> correos, List<DatosExtraEntity> datosextra, List<DireccionEntity> direcciones, List<FotoEntity> fotos, List<MatriculaEntity> matriculas,  List<NumeroTelefonoEntity> telefonos  ) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.dni = dni;
        this.antecedentes = antecedentes;
        this.correos = correos;
        this.fotos = fotos;
        this.telefonos = telefonos;
        this.direcciones = direcciones;
        this.matriculas = matriculas;
        this.datosextra = datosextra;
    }   

    public SospechosoEntity(String nombre, String nacionalidad, String dni) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.dni = dni;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setDni(String dni){
        this.dni = dni;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setAntecedentes(List<AntecedentesEntity> antecedentes) {
        this.antecedentes = antecedentes;
    }

    public void setCorreos(List<CorreoEntity> correos) {
        this.correos = correos;
    }

    public void setTelefonos(List<NumeroTelefonoEntity> telefonos) {
        this.telefonos = telefonos;
    }

    public void setDirecciones(List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }

    public void setMatriculas(List<MatriculaEntity> matriculas) {
        this.matriculas = matriculas;
    }

    public void setDatosextra(List<DatosExtraEntity> datosextra) {
        this.datosextra = datosextra;
    }

    public void setFotos(List<FotoEntity> fotos) {
        this.fotos = fotos;
    }
    
    

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getDni(){
        return dni;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public List<AntecedentesEntity> getAntecedentes() {
        return antecedentes;
    }

    public List<CorreoEntity> getCorreos() {
        return correos;
    }

    public List<NumeroTelefonoEntity> getTelefonos() {
        return telefonos;
    }

    public List<DireccionEntity> getDirecciones() {
        return direcciones;
    }

    public List<MatriculaEntity> getMatriculas() {
        return matriculas;
    }

    public List<DatosExtraEntity> getDatosextra() {
        return datosextra;
    }

    public List<FotoEntity> getFotos() {
        return fotos;
    }

    @Override
    public String toString() {
        return "SospechosoEntity{" + "id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", dni="+dni+", antecedentes=" + antecedentes + ", correos=" + correos + ", telefonos=" + telefonos + ", direcciones=" + direcciones + ", matriculas=" + matriculas + ", datosextra=" + datosextra + ", fotos=" + fotos + '}';
    }
    
    

    
    
}
