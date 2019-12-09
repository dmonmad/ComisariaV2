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
import com.nightm4re.comisariav2.vista.VistaUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nightm4re
 */
public class Controller {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("comisariav2");
    private static Controller controller = null;    
    ArrayList<SospechosoEntity> sosps = null;
    
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
    
    public boolean addSospechoso(String nombre, String dni, String nacionalidad, String antecedentes, String correos, String direcciones , String matriculas, String numeros, String datosextra, String fotos){
        boolean added = false;
        SospechosoEntity sosp = new SospechosoEntity(nombre, dni, nacionalidad);
        
        ArrayList<AntecedentesEntity> antecedenteslist = Utils.AntecedentesStringToList(antecedentes, sosp);
                
        ArrayList<CorreoEntity> correoslist = Utils.CorreosStringToList(correos, sosp);
        
        ArrayList<MatriculaEntity> matriculalist = Utils.MatriculasStringToList(direcciones, sosp);
        
        ArrayList<DireccionEntity> direccionlist = Utils.DireccionesStringToList(matriculas, sosp);
        
        ArrayList<NumeroTelefonoEntity> telefonolist = Utils.NumerosStringToList(numeros, sosp);
        
        ArrayList<DatosExtraEntity> datosextralsit = Utils.DatosExtraStringToList(datosextra, sosp);
        
        ArrayList<FotoEntity> fotoslist = Utils.FotosStringToList(
                Utils.EncodeImagesToFiles(fotos),
                sosp);
        
        sosp.setAntecedentes(antecedenteslist);
        sosp.setCorreos(correoslist);
        sosp.setMatriculas(matriculalist);
        sosp.setDirecciones(direccionlist);
        sosp.setTelefonos(telefonolist);
        sosp.setDatosextra(datosextralsit);
        sosp.setFotos(fotoslist);
        try{
            System.out.println("ENTRAAAAAA");
            SospechosoEntityJpaController sejc = new SospechosoEntityJpaController(emf);
            sejc.create(sosp);
            added = true;
        
        }catch(Exception ex){
            Utils.LogToFile(ex);
        }
        
        return added;
    }
    
    public DefaultTableModel getSospechosos(){
        sosps = new ArrayList<SospechosoEntity>(new SospechosoEntityJpaController(emf).findAllSospechosoEntityEntities());
        for(SospechosoEntity sos : sosps){
            System.out.println(sos);
        }
        return Utils.SospechososToTableModel(sosps);
    }
    
    public Map<Integer, ArrayList> getImagenesFromSosp(int id){
        
        ArrayList<BufferedImage> images = new ArrayList<>();
        ArrayList<String> imagedir = new ArrayList<>();
        Map<Integer, ArrayList> map = new HashMap<Integer, ArrayList>();
        map.put(0, images);
        map.put(1, imagedir);
        
        for(SospechosoEntity se : sosps){
            if(se.getId() == id){
                for(FotoEntity foto : se.getFotos()){
                    imagedir.add(foto.getImagen());
                    System.out.println(foto.getImagen());
                }
                System.out.println(imagedir);
                
                for(String s : imagedir){
                    System.out.println(s);
                    images.add(Utils.DecodeFileToImage(new File(s)));
                    
                }
                System.out.println(imagedir);
                
            }
        }
            
        return map;
    }
    
    public SospechosoEntity getSospechosoByID(int row){
        System.out.println("SELECTING "+row+" / "+sosps.size());
        System.out.println("WOOOORK"+sosps.get(row).toString());
        return sosps.get(row);
    }

    public void deleteSospechoso(int id) {
         try{
            System.out.println("ENTRAAAAAA");
            SospechosoEntityJpaController sejc = new SospechosoEntityJpaController(emf);
            sejc.destroy(sosps.get(id).getId());
                    
        }catch(Exception ex){
            Utils.LogToFile(ex);
        }
    }
    
    public boolean deleteSospechosoIMGByID(int row, String img){
        boolean deleted = false;
        System.out.println("SELECTING "+row+" / "+sosps.size());
        System.out.println("WOOOORK"+sosps.get(row).getNombre());
        if(sosps.get(row).deleteImage(img)){
            deleted = true;
        }
        
        return deleted;
    }

    public void deleteSospechosoIMGByID(int selectedRow, BufferedImage image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
