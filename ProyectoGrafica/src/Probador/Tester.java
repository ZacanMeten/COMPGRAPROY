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
            -0.5f, 0.5f, 0f,    //V0
            -0.5f, -0.5f, 0f,   //V1
            0.5f, -0.5f, 0f,    //V2
            0.5f, 0.5f, 0f      //v3
        };
        
        int[] indices = {
            0,1,3,  //Triangulo superior izq(V0,V1,V3)
            3,1,2    //Triangulo inferior der(V2,V1,V2)
        };
        
        ModeloRaw modelo = loader.cargarToVAO(vertices,indices);
        
                
        while(!Display.isCloseRequested()){
            //Logica del Juego
            
            //Renderizado
            renderer.preparar();
            renderer.render(modelo);
            ManagerDisplay.actualizarDisplay();
        }
        
        loader.Limpieza();
        
        ManagerDisplay.cerrarDisplay();
    }
}
