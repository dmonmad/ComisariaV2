/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nightm4re
 */


@Entity
@Table(name="Sospechosos")
public class Sospechoso implements Serializable {
    
    @Id
    @Column(name="SOSP_ID")
    private long id;
    @Column(name="NOMBRE")
    private String nombre;
@OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<String> nacionalidad;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Antecedentes> antecedentes;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Correo> correos;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<NumeroTelefono> telefonos;
    @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Direccion> direcciones;
   @OneToMany(mappedBy = "sospechoso", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Matricula> matriculas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
