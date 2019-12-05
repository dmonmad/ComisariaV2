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
@Table(name="CORREO")
public class CorreoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CORREO_ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "EMAIL")
    private String correo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private SospechosoEntity sospechoso;
 
    public CorreoEntity(){}

    public CorreoEntity(Long id, String correo, SospechosoEntity sospechoso) {
        this.id = id;
        this.correo = correo;
        this.sospechoso = sospechoso;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setSospechoso(SospechosoEntity sospechoso) {
        this.sospechoso = sospechoso;
    }

    public Long getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public SospechosoEntity getSospechoso() {
        return sospechoso;
    }

    @Override
    public String toString() {
        return "CorreoEntity id=" + id + ", correo=" + correo;
    }
    
    

}


