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
@Table(name="ANTECEDENTES")
public class Antecedentes implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ANTECEDENTES_ID", updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "DELITO")
    private String delito;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private Sospechoso sospechoso;
    
    public Antecedentes(){}
    
    public Antecedentes(Long id, String titulo, Sospechoso sosp) {
        this.id = id;
        this.delito = titulo;
        this.sospechoso = sosp;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return delito;
    }

    public Sospechoso getSospechoso() {
        return sospechoso;
    }

    public void setDelito(String delito) {
        this.delito = delito;
    }

    public void setSospechoso(Sospechoso sosp) {
        this.sospechoso = sosp;
    }
    
}
