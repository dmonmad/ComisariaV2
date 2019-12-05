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
@Table(name="NUMEROTELEFONO")
public class NumeroTelefonoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="TELEFONO_ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "NUMERO")
    private String numero;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private SospechosoEntity sospechoso;

    public NumeroTelefonoEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public SospechosoEntity getSospechoso() {
        return sospechoso;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSospechoso(SospechosoEntity sospechoso) {
        this.sospechoso = sospechoso;
    }

    @Override
    public String toString() {
        return "NumeroTelefonoEntity id=" + id + ", numero=" + numero ;
    }
    
    
    
}
