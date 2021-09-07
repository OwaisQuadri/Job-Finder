/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jobfinder;

/**
 *
 * @author Owais
 */
public class Main {
    
    
    public static void main(String[] args){
        //turning off warnings ofr HTMLUnit
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
        //init window
        Menu m=new Menu();
        m.setVisible(true);
    }
}
