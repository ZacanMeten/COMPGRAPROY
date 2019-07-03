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
import RenderEngine.EntidadRenderizador;
import Entidades.Entidad;
import Entidades.Luz;
import Modelos.ModeloTexturizado;
import ObjConvertidor.ModeloData;
import ObjConvertidor.ObjArchivoCargador;
import RenderEngine.MaestroRenderizador;
import RenderEngine.OBJcargador;
import Sombreadores.StaticShader;
import Terrenos.Terreno;
import Texturas.ModelTexture;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        
        ModeloData data =  ObjArchivoCargador.cargarOBJ("murobasico");
        
        ModeloRaw ArbolModelo = loader.cargarToVAO(data.getVertices(), data.getTexturaCordenadas(), data.getNormales(), data.getIndices());
        
        ModeloTexturizado staticModel = new ModeloTexturizado(ArbolModelo, new ModelTexture( loader.cargarTextura("MuroTexture") ));
        staticModel.getTexture().setHasTransparencia(false);
        staticModel.getTexture().setUsarLuzFalsa(true);
        //Muros
        List<Entidad> entidades = new ArrayList<Entidad>();
        Random ran = new Random();
        for(int i=0;i<150;i++){
            entidades.add(new Entidad(staticModel,new Vector3f(ran.nextFloat()*1600 - 800, -10,ran.nextFloat() * -800),0,ran.nextFloat()*90,0,13));
        }
        
        Luz luz = new Luz(new Vector3f(0,20000, -400), new Vector3f(1, 1, 1));  //Posicion de la luz y su color
        
        Terreno terreno = new Terreno(0, 0,loader,new ModelTexture(loader.cargarTextura("grass")));
        Terreno terreno2 = new Terreno(1, 0,loader,new ModelTexture(loader.cargarTextura("grass")));
        
        Camara camara = new Camara(new Vector3f(0, 0, -10));
        
        MaestroRenderizador renderer = new MaestroRenderizador();
        while(!Display.isCloseRequested()){
            //Logica del Juego
            camara.Mover();
            luz.setPosicion(camara.getPosicion());
            
            //Renderizado
            renderer.procesarTerreno(terreno);
            renderer.procesarTerreno(terreno2);
            for(Entidad entity:entidades){
                renderer.procesarEntidad(entity);
            }
            renderer.renderizar(luz, camara);
            ManagerDisplay.actualizarDisplay();
        }
        
        renderer.limpiar();
        loader.Limpieza();
        ManagerDisplay.cerrarDisplay();
    }
}
