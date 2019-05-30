/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Probador;

import Display.ManagerDisplay;
import org.lwjgl.opengl.Display;

/**
 *
 * @author reant
 */
public class Tester {
    
    public static void main(String[] args) {
        ManagerDisplay.crearDisplay();
        while(!Display.isCloseRequested()){
            //Logica del Juego
            
            //Refresacado
            ManagerDisplay.actualizarDisplay();
        }
        
        ManagerDisplay.cerrarDisplay();
    }
}
