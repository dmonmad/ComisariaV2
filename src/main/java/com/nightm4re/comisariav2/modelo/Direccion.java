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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Nightm4re
 */
    
    @Entity
@Table(name="DIRECCION")
public class Direccion implements Serializable {
    
    @Id
    @Column(name="DIRECCION_ID")
    private Long id;
    
    @Column(name = "DIRECCION")
    private String direccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private Sospechoso sospechoso;
    
    public Direccion(){}

    public Direccion(Long id, String direccion, Sospechoso sospechoso) {
        this.id = id;
        this.direccion = direccion;
        this.sospechoso = sospechoso;
    }

    public Long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public Sospechoso getSospechoso() {
        return sospechoso;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSospechoso(Sospechoso sospechoso) {
        this.sospechoso = sospechoso;
    }
    
    
    
}
