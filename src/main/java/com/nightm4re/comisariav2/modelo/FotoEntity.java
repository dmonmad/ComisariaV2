/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.modelo;

import java.io.File;
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
@Table(name="FOTO")
public class FotoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="FOTO_ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "IMAGEN")
    private String imagen;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private SospechosoEntity sospechoso;

    public FotoEntity() {
    }
    

    public FotoEntity(Long id, String imagen, SospechosoEntity sospechoso) {
        this.id = id;
        this.imagen = imagen;
        this.sospechoso = sospechoso;
    }

    public Long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public SospechosoEntity getSospechoso() {
        return sospechoso;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setSospechoso(SospechosoEntity sospechoso) {
        this.sospechoso = sospechoso;
    }

    @Override
    public String toString() {
        return "FotoEntity id=" + id + ", imagen=" + imagen;
    }
    
    
    
}
