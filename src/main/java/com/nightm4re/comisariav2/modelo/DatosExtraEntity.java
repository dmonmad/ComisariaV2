/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Nightm4re
 */
@Entity
@Table(name="DATOSEXTRA")
public class DatosExtraEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="DATO_ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "DATO")
    private String dato;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private SospechosoEntity sospechoso;
    
    public DatosExtraEntity(){}

    public DatosExtraEntity(Long id, String dato, SospechosoEntity sospechoso) {
        this.id = id;
        this.dato = dato;
        this.sospechoso = sospechoso;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setSospechoso(SospechosoEntity sospechoso) {
        this.sospechoso = sospechoso;
    }

    public Long getId() {
        return id;
    }

    public String getDato() {
        return dato;
    }

    public SospechosoEntity getSospechoso() {
        return sospechoso;
    }

    @Override
    public String toString() {
        return "DatosExtraEntity id=" + id + ", dato=" + dato;
    }
    
    
    
}

