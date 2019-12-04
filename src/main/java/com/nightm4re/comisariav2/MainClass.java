/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2;

import com.nightm4re.comisariav2.modelo.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nightm4re
 */
public class MainClass {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("comisariav2");
    
    public static void main(String[] args){
        
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        List<Correo> email = new ArrayList();
        Correo c = new Correo();
        c.setCorreo("blabla@hotmail.com");
        email.add(c);
        
        manager.persist(c);
   
        List<Antecedentes> antecedente = new ArrayList();
        Antecedentes an = new Antecedentes();
        an.setDelito("Atraco");
        antecedente.add(an);
        
        manager.persist(an);
        
        List<DatosExtra> datosextra = new ArrayList();
        DatosExtra da = new DatosExtra();
        da.setDato("Peligroso");
        datosextra.add(da);
        
        manager.persist(da);
        
        List<Direccion> direccion = new ArrayList();
        Direccion dir = new Direccion();
        dir.setDireccion("Calle de la piruleta");
        direccion.add(dir);
        
        manager.persist(dir);
        
        
        
        List<Foto> foto = new ArrayList();
        Foto fo = new Foto();
        fo.setImagen("/wtf/blabla.jpg");
        foto.add(fo);
        
        manager.persist(fo);
        
        
        
        List<Matricula> matricula = new ArrayList();
        Matricula mat = new Matricula();
        mat.setMatricula("2513 JKL");
        matricula.add(mat);
        
        manager.persist(mat);
        
        
        
        List<NumeroTelefono> telefono = new ArrayList();
        NumeroTelefono tel = new NumeroTelefono();
        tel.setNumero("600100200");
        telefono.add(tel);
        
        manager.persist(tel);
        
        
        
        
        Sospechoso sosp = new Sospechoso("Alfonso", "Española", antecedente, email, datosextra, direccion, foto, matricula,  telefono);
        
        manager.persist(sosp);
        
        manager.getTransaction().commit();
        manager.close();
        
        
    }
    
}
