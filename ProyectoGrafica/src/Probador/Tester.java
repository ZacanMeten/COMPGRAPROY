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
import Entidades.Luz;
import Modelos.ModeloTexturizado;
import RenderEngine.OBJcargador;
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
        
        ModeloRaw modelo = OBJcargador.cargarOBJmodel("Monstruo", loader);
        
        ModeloTexturizado staticModel = new ModeloTexturizado(modelo, new ModelTexture( loader.cargarTextura("MonstruoTexture2") ));
        
        Entidad entidad = new Entidad(staticModel, new Vector3f( 0, 0, -5) , 0, 0, 0, 1);
        Luz luz = new Luz(new Vector3f(0, 0, -2), new Vector3f(1, 1, 1));  //Posicion de la luz y su color
        
        Camara camara = new Camara();
        
        while(!Display.isCloseRequested()){
            //Logica del Juego
            entidad.IncrementarRotacion(0, 1, 0);
            camara.Mover();
            //Renderizado
            renderer.preparar();
            shader.empezar();
            shader.cargarLuz(luz);
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
