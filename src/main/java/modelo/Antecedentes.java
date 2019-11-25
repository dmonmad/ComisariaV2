/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
public class Antecedentes {
    
    @Id
    @Column(name="ANTECEDENTES_ID")
    private Long id;
    
    @Column(name = "DEFINICION")
    private String titulo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private Sospechoso sosp;
    
    
    public Antecedentes(Long id, String titulo, Sospechoso sosp) {
        this.id = id;
        this.titulo = titulo;
        this.sosp = sosp;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Sospechoso getSosp() {
        return sosp;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSosp(Sospechoso sosp) {
        this.sosp = sosp;
    }
    
    
    
}
