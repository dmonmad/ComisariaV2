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
@Table(name="CORREO")
public class Correo implements Serializable {
    
    @Id
    @Column(name="CORREO_ID")
    private Long id;
    
    @Column(name = "EMAIL")
    private String delito;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private Sospechoso sospechoso;
 
    public Correo(){}

    public Correo(Long id, String delito, Sospechoso sospechoso) {
        this.id = id;
        this.delito = delito;
        this.sospechoso = sospechoso;
    }

    public void setDelito(String delito) {
        this.delito = delito;
    }

    public void setSospechoso(Sospechoso sospechoso) {
        this.sospechoso = sospechoso;
    }

    public Long getId() {
        return id;
    }

    public String getDelito() {
        return delito;
    }

    public Sospechoso getSospechoso() {
        return sospechoso;
    }

}


