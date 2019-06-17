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
import RenderEngine.MaestroRenderizador;
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
        
        ModeloRaw modelo = OBJcargador.cargarOBJmodel("Monstruo2", loader);
        
        ModeloTexturizado staticModel = new ModeloTexturizado(modelo, new ModelTexture( loader.cargarTextura("MonsTexture") ));
        ModelTexture textura = staticModel.getTexture();
        textura.setBrilloDamper(10); 
        textura.setReflectividad(1); //Set nivel de la reflectividad
        
        Entidad monstruo1 = new Entidad(staticModel, new Vector3f( 0, 0, -5) , 0, 0, 0, 1);
        Luz luz = new Luz(new Vector3f(5, 5, 5), new Vector3f(1, 1, 1));  //Posicion de la luz y su color
        
        Camara camara = new Camara();
        
        MaestroRenderizador renderer = new MaestroRenderizador();
        while(!Display.isCloseRequested()){
            //Logica del Juego
            camara.Mover();
            //Renderizado
            renderer.procesarEntidad(monstruo1);
            renderer.renderizar(luz, camara);
            ManagerDisplay.actualizarDisplay();
        }
        
        renderer.limpiar();
        loader.Limpieza();
        ManagerDisplay.cerrarDisplay();
    }
}
