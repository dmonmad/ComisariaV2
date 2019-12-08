/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.vista;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;

/**
 *
 * @author Nightm4re
 */
public class VistaData {
    
        ArrayList<String> imagespath = new ArrayList<>();
        public HashMap<JLabel, String> jlabelurlmap = new HashMap<JLabel, String>();
    
    public void addJlabelUrl(JLabel key, String value) {
        jlabelurlmap.put(key, value);
    }
    
    public String getAllUrls() {
        ArrayList<String> urls = new ArrayList<>();
        for(String s : jlabelurlmap.values()){
            urls.add(s);
        }
        StringBuilder sb = new StringBuilder("");
        
        for(String s : urls){
            sb.append(s+"\n");
        }
        
        return sb.toString();
    }
    
    public void removeJlabelUrl(JLabel key){
        jlabelurlmap.remove(key);
    }
    
    public void removeAllMap(){
        jlabelurlmap.clear();
    }
    
    
}
