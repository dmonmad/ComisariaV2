/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.vista;

import com.nightm4re.comisariav2.modelo.SospechosoEntity;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nightm4re
 */
public class VistaUtils {

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

}
