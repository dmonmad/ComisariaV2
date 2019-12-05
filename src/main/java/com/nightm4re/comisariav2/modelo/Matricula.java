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
@Table(name="MATRICULA")
public class Matricula implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MATRICULA_ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "MATRICULA")
    private String matricula;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private Sospechoso sospechoso;

    public Matricula() {
    }

    public Matricula(Long id, String matricula, Sospechoso sospechoso) {
        this.id = id;
        this.matricula = matricula;
        this.sospechoso = sospechoso;
    }

    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public Sospechoso getSospechoso() {
        return sospechoso;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setSospechoso(Sospechoso sospechoso) {
        this.sospechoso = sospechoso;
    }
    
    
    
}
