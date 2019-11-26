/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nightm4re
 */
public class MainClass {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("comisaria");
    
    public static void main(String[] args){
        
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        
    }
    
}
