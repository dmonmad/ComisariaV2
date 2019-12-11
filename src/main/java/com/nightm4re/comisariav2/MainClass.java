/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2;

import com.nightm4re.comisariav2.modelo.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Nightm4re
 */
public class MainClass {
    
    
    
    public static void main(String[] args){
        
        Controller.getController().Start();
        
//        meterdatos();
//        
//        leerdatos();
        
        
    }

    private static void meterdatos() {
//        emf=Persistence.createEntityManagerFactory("comisariav2");
//        manager=emf.createEntityManager();
//        manager.getTransaction().begin();
        
        SospechosoEntity sosp = null;
        
//        manager.persist(sosp);
        
        List<CorreoEntity> email = new ArrayList();
        CorreoEntity c = new CorreoEntity();
        c.setCorreo("blabla@hotmail.com");
        c.setSospechoso(sosp);
        email.add(c);
        
   
        List<AntecedentesEntity> antecedente = new ArrayList();
        AntecedentesEntity an = new AntecedentesEntity();
        an.setDelito("Atraco");
        an.setSospechoso(sosp);
        antecedente.add(an);
        
        
        
        List<DatosExtraEntity> datosextra = new ArrayList();
        DatosExtraEntity da = new DatosExtraEntity();
        da.setDato("Peligroso");
        da.setSospechoso(sosp);
        datosextra.add(da);
        
        
        List<DireccionEntity> direccion = new ArrayList();
        DireccionEntity dir = new DireccionEntity();
        dir.setDireccion("Calle de la piruleta");
        dir.setSospechoso(sosp);
        direccion.add(dir);
        
        
        
        List<FotoEntity> foto = new ArrayList();
        FotoEntity fo = new FotoEntity();
        fo.setImagen("/wtf/blabla.jpg");
        fo.setSospechoso(sosp);
        foto.add(fo);
        
        
        
        List<MatriculaEntity> matricula = new ArrayList();
        MatriculaEntity mat = new MatriculaEntity();
        mat.setMatricula("2513 JKL");
        mat.setSospechoso(sosp);
        matricula.add(mat);
        
        
        
        List<NumeroTelefonoEntity> telefono = new ArrayList();
        NumeroTelefonoEntity tel = new NumeroTelefonoEntity();
        tel.setNumero("600100200");
        tel.setSospechoso(sosp);;
        telefono.add(tel);
        
        sosp = new SospechosoEntity("Alfonso", "Marroquí", "31851121K");
        sosp.setAntecedentes(antecedente);
        sosp.setCorreos(email);
        sosp.setDatosextra(datosextra);
        sosp.setFotos(foto);
        sosp.setMatriculas(matricula);
        sosp.setTelefonos(telefono);
        sosp.setDirecciones(direccion);
        
//        SospechosoEntityJpaController sospcont = new SospechosoEntityJpaController();
//        sospcont.create(sosp);
        
//        manager.persist(c);
//        manager.persist(an);
//        manager.persist(da);
//        manager.persist(dir);
//        manager.persist(fo);
//        manager.persist(mat);
//        manager.persist(tel);
//
//
//        manager.getTransaction().commit();
//        manager.close();
    }

    private static void leerdatos() {
        //EntityManager manager = emf.createEntityManager();
        //manager.getTransaction().begin();
        

        //List<SospechosoEntity> misempleados=manager.createQuery("FROM SospechosoEntity", SospechosoEntity.class).getResultList();
        //System.out.println("Hay "+misempleados.size()+" empleados");
        
        //for(SospechosoEntity e : misempleados){
            //System.out.println(e);
        //}

        
        //manager.getTransaction().commit();
        //manager.close();
    }
    
}
