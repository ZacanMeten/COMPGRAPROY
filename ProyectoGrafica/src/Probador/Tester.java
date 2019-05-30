/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Probador;

import Display.Cargador;
import Display.ManagerDisplay;
import Display.ModeloRaw;
import Display.Renderizador;
import org.lwjgl.opengl.Display;

/**
 *
 * @author reant
 */
public class Tester {
    
    public static void main(String[] args) {
        ManagerDisplay.crearDisplay();
        
        Cargador loader = new Cargador();
        Renderizador renderer = new Renderizador();
        
        //OpenGL expects vertices to be defined counter clockwise by default
        float[] vertices ={
            //Triangulo inferior izquierdo
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            //Triangulo superior derecho
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
        };
        
        ModeloRaw modelo = loader.cargarToVAO(vertices);
        
                
        while(!Display.isCloseRequested()){
            //Logica del Juego
            
            //Renderizado
            renderer.render(modelo);
            ManagerDisplay.actualizarDisplay();
        }
        
        loader.Limpieza();
        
        ManagerDisplay.cerrarDisplay();
    }
}
