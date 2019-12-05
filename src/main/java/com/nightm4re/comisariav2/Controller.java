/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2;

import com.nightm4re.comisariav2.vista.VistaPrincipal;

/**
 *
 * @author Nightm4re
 */
public class Controller {
    
    private static Controller controller = null;
    
    
    
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
    
}
