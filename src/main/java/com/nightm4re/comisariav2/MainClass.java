/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2;

import com.nightm4re.comisariav2.modelo.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nightm4re
 */
public class MainClass {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("comisariav2");
    
    public static void main(String[] args){
        
        meterdatos();
        
//        leerdatos();
        
        
    }

    private static void meterdatos() {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        Sospechoso sosp = new Sospechoso("Carlos", "Albanés");
        
        manager.persist(sosp);
        
        List<Correo> email = new ArrayList();
        Correo c = new Correo();
        c.setCorreo("blabla@hotmail.com");
        c.setSospechoso(sosp);
        email.add(c);
        
   
        List<Antecedentes> antecedente = new ArrayList();
        Antecedentes an = new Antecedentes();
        an.setDelito("Atraco");
        an.setSospechoso(sosp);
        antecedente.add(an);
        
        
        
        List<DatosExtra> datosextra = new ArrayList();
        DatosExtra da = new DatosExtra();
        da.setDato("Peligroso");
        da.setSospechoso(sosp);
        datosextra.add(da);
        
        
        List<Direccion> direccion = new ArrayList();
        Direccion dir = new Direccion();
        dir.setDireccion("Calle de la piruleta");
        dir.setSospechoso(sosp);
        direccion.add(dir);
        
        
        
        List<Foto> foto = new ArrayList();
        Foto fo = new Foto();
        fo.setImagen("/wtf/blabla.jpg");
        fo.setSospechoso(sosp);
        foto.add(fo);
        
        
        
        List<Matricula> matricula = new ArrayList();
        Matricula mat = new Matricula();
        mat.setMatricula("2513 JKL");
        mat.setSospechoso(sosp);
        matricula.add(mat);
        
        
        
        List<NumeroTelefono> telefono = new ArrayList();
        NumeroTelefono tel = new NumeroTelefono();
        tel.setNumero("600100200");
        tel.setSospechoso(sosp);;
        telefono.add(tel);
        
        manager.persist(c);
        manager.persist(an);
        manager.persist(da);
        manager.persist(dir);
        manager.persist(fo);
        manager.persist(mat);
        manager.persist(tel);


        manager.getTransaction().commit();
        manager.close();
    }

    private static void leerdatos() {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        Query query = manager.createQuery("select SOSPECHOSOS");
        Collection<Sospechoso> sos = (Collection<Sospechoso>) query.getResultList();
        
        for(Sospechoso s : sos){
            System.out.println(s);
        }

        
        manager.getTransaction().commit();
        manager.close();
    }
    
}
