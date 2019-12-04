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
@Table(name="NUMEROTELEFONO")
public class NumeroTelefono implements Serializable {
    
    @Id
    @Column(name="TELEFONO_ID")
    private Long id;
    
    @Column(name = "NUMERO")
    private String numero;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private Sospechoso sospechoso;

    public NumeroTelefono() {
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public Sospechoso getSospechoso() {
        return sospechoso;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSospechoso(Sospechoso sospechoso) {
        this.sospechoso = sospechoso;
    }
    
    
    
}
