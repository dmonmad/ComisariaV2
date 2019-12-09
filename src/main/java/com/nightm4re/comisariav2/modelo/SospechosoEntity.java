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
import javax.persistence.FetchType;
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
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
    private List<AntecedentesEntity> antecedentes;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
    private List<CorreoEntity> correos;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
    private List<NumeroTelefonoEntity> telefonos;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
    private List<DireccionEntity> direcciones;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
    private List<MatriculaEntity> matriculas;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
    private List<DatosExtraEntity> datosextra;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
    private List<FotoEntity> fotos;

   public SospechosoEntity(){}

    public SospechosoEntity(String nombre, String dni, String nacionalidad, List<AntecedentesEntity> antecedentes, List<CorreoEntity> correos, List<DatosExtraEntity> datosextra, List<DireccionEntity> direcciones, List<FotoEntity> fotos, List<MatriculaEntity> matriculas,  List<NumeroTelefonoEntity> telefonos  ) {
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

    public SospechosoEntity(String nombre, String dni, String nacionalidad) {
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
    
    public String getAntecedentesString(){
        StringBuilder sb = new StringBuilder("");
        
        for(AntecedentesEntity ae : antecedentes){
            sb.append(ae.getDelito()+"\n");
        }
        
        return sb.toString();
    }
    
    public String getDireccionesString(){
        StringBuilder sb = new StringBuilder("");
        
        for(DireccionEntity ae : direcciones){
            sb.append(ae.getDireccion()+"\n");
        }
        
        return sb.toString();
    }
    
    public String getCorreosString(){
        StringBuilder sb = new StringBuilder("");
        
        for(CorreoEntity ae : correos){
            sb.append(ae.getCorreo()+"\n");
        }
        
        return sb.toString();
    }
    
    public String getMatriculasString(){
        StringBuilder sb = new StringBuilder("");
        
        for(MatriculaEntity ae : matriculas){
            sb.append(ae.getMatricula()+"\n");
        }
        
        return sb.toString();
    }
    
    public String getTelefonosString(){
        StringBuilder sb = new StringBuilder("");
        
        for(NumeroTelefonoEntity ae : telefonos){
            sb.append(ae.getNumero()+"\n");
        }
        
        return sb.toString();
    }
    
    public String getDatosextraString(){
        StringBuilder sb = new StringBuilder("");
        
        for(DatosExtraEntity ae : datosextra){
            sb.append(ae.getDato()+"\n");
        }
        
        return sb.toString();
    }
    
    public String getFotosString(){
        StringBuilder sb = new StringBuilder("");
        
        for(FotoEntity ae : fotos){
            sb.append(ae.getImagen()+"\n");
        }
        
        return sb.toString();
    }
    
    public boolean deleteImage(String image){
        boolean deleted = false;
        for(FotoEntity fe : fotos){
            if(fe.getImagen() == image){
                fotos.remove(fe);
                deleted = true;
            }
        }
        return deleted;
    }

    
    
}
