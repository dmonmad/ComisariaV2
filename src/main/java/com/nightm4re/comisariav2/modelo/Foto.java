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
public class Foto implements Serializable {
    
    @Id
    @Column(name="ANTECEDENTES_ID")
    private Long id;
    
    @Column(name = "IMAGEN")
    private File imagen;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SOSP_ID")
    private Sospechoso sospechoso;
    
}
