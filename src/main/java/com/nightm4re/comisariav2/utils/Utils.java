/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.utils;

import com.nightm4re.comisariav2.modelo.AntecedentesEntity;
import com.nightm4re.comisariav2.modelo.CorreoEntity;
import com.nightm4re.comisariav2.modelo.DatosExtraEntity;
import com.nightm4re.comisariav2.modelo.DireccionEntity;
import com.nightm4re.comisariav2.modelo.FotoEntity;
import com.nightm4re.comisariav2.modelo.MatriculaEntity;
import com.nightm4re.comisariav2.modelo.NumeroTelefonoEntity;
import com.nightm4re.comisariav2.modelo.SospechosoEntity;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nightm4re
 */
public class Utils {

    final static String key = "Mary has one cat1";

    ;

    public static ArrayList<AntecedentesEntity> AntecedentesStringToList(String antecedentes, SospechosoEntity sospechoso) {
        ArrayList<AntecedentesEntity> antecedenteslist = new ArrayList<>();
        String[] antec = antecedentes.split("\\n");
        for (int i = 0; i < antec.length; i++) {
            AntecedentesEntity ant = new AntecedentesEntity();
            ant.setDelito(antec[i]);
            ant.setSospechoso(sospechoso);
            antecedenteslist.add(ant);
        }
        return antecedenteslist;
    }

    public static ArrayList<CorreoEntity> CorreosStringToList(String correos, SospechosoEntity sospechoso) {
        ArrayList<CorreoEntity> correoslist = new ArrayList<>();
        String[] corrarray = correos.split("\\n");
        for (int i = 0; i < corrarray.length; i++) {
            CorreoEntity dir = new CorreoEntity();
            dir.setCorreo(corrarray[i]);
            dir.setSospechoso(sospechoso);
            correoslist.add(dir);
        }
        return correoslist;
    }

    public static ArrayList<DireccionEntity> DireccionesStringToList(String direcciones, SospechosoEntity sospechoso) {
        ArrayList<DireccionEntity> direccioneslist = new ArrayList<>();
        String[] dirarray = direcciones.split("\\n");
        for (int i = 0; i < dirarray.length; i++) {
            DireccionEntity dir = new DireccionEntity();
            dir.setDireccion(dirarray[i]);
            dir.setSospechoso(sospechoso);
            direccioneslist.add(dir);
        }
        return direccioneslist;
    }

    public static ArrayList<MatriculaEntity> MatriculasStringToList(String matriculas, SospechosoEntity sospechoso) {
        ArrayList<MatriculaEntity> matriculaslist = new ArrayList<>();
        String[] matrarray = matriculas.split("\\n");
        for (int i = 0; i < matrarray.length; i++) {
            MatriculaEntity mat = new MatriculaEntity();
            mat.setMatricula(matrarray[i]);
            mat.setSospechoso(sospechoso);
            matriculaslist.add(mat);
        }
        return matriculaslist;
    }

    public static ArrayList<NumeroTelefonoEntity> NumerosStringToList(String numeros, SospechosoEntity sospechoso) {
        ArrayList<NumeroTelefonoEntity> antecedenteslist = new ArrayList<>();
        String[] numarray = numeros.split("\\n");
        for (int i = 0; i < numarray.length; i++) {
            NumeroTelefonoEntity num = new NumeroTelefonoEntity();
            num.setNumero(numarray[i]);
            num.setSospechoso(sospechoso);
            antecedenteslist.add(num);
        }
        return antecedenteslist;
    }

    public static ArrayList<DatosExtraEntity> DatosExtraStringToList(String datosextra, SospechosoEntity sospechoso) {
        ArrayList<DatosExtraEntity> datosextralist = new ArrayList<>();
        String[] datext = datosextra.split("\\n");
        for (int i = 0; i < datext.length; i++) {
            DatosExtraEntity dato = new DatosExtraEntity();
            dato.setDato(datext[i]);
            dato.setSospechoso(sospechoso);
            datosextralist.add(dato);
        }
        return datosextralist;
    }

    public static ArrayList<FotoEntity> FotosStringToList(String fotos, SospechosoEntity sospechoso) {
        ArrayList<FotoEntity> fotoslist = new ArrayList<>();
        String[] datext = fotos.split("\\n");
        for (int i = 0; i < datext.length; i++) {
            FotoEntity dato = new FotoEntity();
            dato.setImagen(datext[i]);
            dato.setSospechoso(sospechoso);
            fotoslist.add(dato);
        }
        return fotoslist;
    }

    public static String EncodeImagesToFiles(String fotos) {
        StringBuilder sb = new StringBuilder("");

        String[] datext = fotos.split("\\n");

        for (int i = 0; i < datext.length; i++) {
            try {
                
                File archivo = new File(datext[i]);
                if (archivo.canRead()) {
                    File st = generateRandomFile();
                    EncodingUtils.encode(archivo, st);
                    sb.append(st.getPath()+"\n");

                }else{
                    JOptionPane.showMessageDialog(null, "No se puede leer el archivo.", "ERROR DE LECTURA", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                LogToFile(e);
            }

        }

        return sb.toString();
    }

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    public static void LogToFile(Exception e) {

        try {
            FileWriter fw = new FileWriter("log.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw, true);
            e.printStackTrace(out);
        } catch (Exception ie) {
            System.out.println("Could not write Exception to file: " + ie);
        }

    }
    
    public static DefaultTableModel SospechososToTableModel(ArrayList<SospechosoEntity> sospechosos) {

        String[] col = {"Nombre", "DNI", "Nacionalidad"};
        DefaultTableModel tb = new DefaultTableModel(col, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (SospechosoEntity s : sospechosos) {
            Object[] x = {s.getNombre(), s.getDni(), s.getNacionalidad()};

            tb.addRow(x);
        }

        return tb;
    }

    public static void EncodeImageToFile(String file) {

        try {

            File archivo = new File(file);
            if (archivo.canRead()) {

                EncodingUtils.encode(new File(file), generateRandomFile());

                JOptionPane.showMessageDialog(null, "Hubo un error leyendo el archivo.", "ERROR DE LECTURA", JOptionPane.ERROR_MESSAGE);

            }

        } catch (HeadlessException e) {

        }

    }
    
    public static BufferedImage DecodeFileToImage(File file){
        BufferedImage bi = null;
        
        try {

            
            if (file.canRead()) {

                bi = EncodingUtils.decode(file);


            }else{
                JOptionPane.showMessageDialog(null, "Hubo un error leyendo el archivo.", "ERROR DE LECTURA", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException e) {
            Utils.LogToFile(e);
        }
        return bi;
        
    }

    public static File generateRandomFile() {
        int nombre = 0;
        boolean isvalid = false;
        File enc = null;
        while (!isvalid) {
            nombre = (int) (1000000 + (Math.random() * 999999));
            System.out.println("WHILE");
            try {
                enc = new File("./database/" + ".imgfiles/" + nombre + ".data");
                if (!enc.exists() && !enc.isDirectory()) {
                    isvalid = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return enc;
    }

    public static void deleteImage(String codedpath) {
        System.out.println(codedpath);
        File e = new File(codedpath);
        
        if(!e.delete()){
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
