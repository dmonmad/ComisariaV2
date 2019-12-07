/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2;

import com.nightm4re.comisariav2.controllers.SospechosoEntityJpaController;
import com.nightm4re.comisariav2.modelo.AntecedentesEntity;
import com.nightm4re.comisariav2.modelo.CorreoEntity;
import com.nightm4re.comisariav2.modelo.DatosExtraEntity;
import com.nightm4re.comisariav2.modelo.DireccionEntity;
import com.nightm4re.comisariav2.modelo.FotoEntity;
import com.nightm4re.comisariav2.modelo.MatriculaEntity;
import com.nightm4re.comisariav2.modelo.NumeroTelefonoEntity;
import com.nightm4re.comisariav2.modelo.SospechosoEntity;
import com.nightm4re.comisariav2.utils.Utils;
import com.nightm4re.comisariav2.vista.VistaPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nightm4re
 */
public class Controller {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("comisariav2");
    private static Controller controller = null;    
    
    ArrayList<String> imagespath = new ArrayList<>();
    
    public void Start(){

        VistaPrincipal vista = new VistaPrincipal();
        vista.setVisible(true);
    }
    
    public static Controller getController(){
        if(controller == null){
            controller = new Controller();
        }
        
        return controller;
    }
    
    public String addSospechoso(String nombre, String dni, String nacionalidad, String antecedentes, String correos, String direcciones , String matriculas, String numeros, String datosextra){
        StringBuilder sb = new StringBuilder("Hay errores en: ");
        
        SospechosoEntity sosp = new SospechosoEntity(nombre, dni, nacionalidad);
        
        ArrayList<AntecedentesEntity> antecedenteslist = Utils.AntecedentesStringToList(antecedentes, sosp);
        
        ArrayList<CorreoEntity> correoslist = Utils.CorreosStringToList(correos, sosp);
        
        ArrayList<MatriculaEntity> matriculalist = Utils.MatriculasStringToList(direcciones, sosp);
        
        ArrayList<DireccionEntity> direccionlist = Utils.DireccionesStringToList(matriculas, sosp);
        
        ArrayList<NumeroTelefonoEntity> telefonolist = Utils.NumerosStringToList(numeros, sosp);
        
        ArrayList<DatosExtraEntity> datosextralsit = Utils.DatosExtraStringToList(datosextra, sosp);
        
        ArrayList<FotoEntity> fotoslist = new ArrayList<>();
        FotoEntity e = new FotoEntity();
        e.setImagen("/src/image");
        e.setSospechoso(sosp);
        
        sosp.setAntecedentes(antecedenteslist);
        sosp.setCorreos(correoslist);
        sosp.setMatriculas(matriculalist);
        sosp.setDirecciones(direccionlist);
        sosp.setTelefonos(telefonolist);
        sosp.setDatosextra(datosextralsit);
        sosp.setFotos(fotoslist);
        
        System.out.println("ENTRAAAAAA");
        SospechosoEntityJpaController sejc = new SospechosoEntityJpaController(emf);
        sejc.create(sosp);
        
        return sb.toString();
    }
    
    public void addImagesPath(String s){
        imagespath.add(s);
    }
    
    public void clearImagesPath(){
        imagespath.clear();
    }
    
    public void removeImagesPath(String s){
        imagespath.remove(s);
    }
    
}
