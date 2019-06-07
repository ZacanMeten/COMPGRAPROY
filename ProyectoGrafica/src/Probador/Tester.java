/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Probador;

import Entidades.Camara;
import RenderEngine.Cargador;
import RenderEngine.ManagerDisplay;
import Modelos.ModeloRaw;
import RenderEngine.Renderizador;
import Entidades.Entidad;
import Modelos.ModeloTexturizado;
import Sombreadores.StaticShader;
import Texturas.ModelTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class Tester {
    
    public static void main(String[] args) {
        ManagerDisplay.crearDisplay();
        Cargador loader = new Cargador();        
        //Crear un shader static
        StaticShader shader = new StaticShader();
        
        Renderizador renderer = new Renderizador(shader);
        //OpenGL expects vertices to be defined counter clockwise by default
        float[] vertices ={            
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                0.5f,-0.5f,0,   
                0.5f,0.5f,0,        
                 
                -0.5f,0.5f,1,   
                -0.5f,-0.5f,1,  
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                0.5f,0.5f,0,    
                0.5f,-0.5f,0,   
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                -0.5f,-0.5f,1,  
                -0.5f,0.5f,1,
                 
                -0.5f,0.5f,1,
                -0.5f,0.5f,0,
                0.5f,0.5f,0,
                0.5f,0.5f,1,
                 
                -0.5f,-0.5f,1,
                -0.5f,-0.5f,0,
                0.5f,-0.5f,0,
                0.5f,-0.5f,1
        };
        
        int[] indices = {
                0,1,3,  
                3,1,2,  
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,   
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22
        };
        
        float[] coordenadasTextura = {
                0,0,
                0,1,
                1,1,
                1,0,            
                0,0,
                0,1,
                1,1,
                1,0,            
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0
        };
        
        ModeloRaw modelo = loader.cargarToVAO(vertices, coordenadasTextura, indices);
        
        ModeloTexturizado staticModel = new ModeloTexturizado(modelo, new ModelTexture( loader.cargarTextura("Muro") ));
        
        Entidad entidad = new Entidad(staticModel, new Vector3f( 0, 0,-5) , 0, 0, 0, 1);
        
        Camara camara = new Camara();
        
        while(!Display.isCloseRequested()){
            //Logica del Juego
            entidad.IncrementarRotacion(1, 1, 0);
            camara.Mover();
            //Renderizado
            renderer.preparar();
            shader.empezar();
            shader.cargarViewMatrix(camara);
            renderer.render(entidad, shader);
            shader.parar();
            ManagerDisplay.actualizarDisplay();
        }
        
        shader.limpiar();
        
        loader.Limpieza();
        
        ManagerDisplay.cerrarDisplay();
    }
}
