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
import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Luz;
import Entidades.Monstruo;
import Modelos.ModeloTexturizado;
import ObjConvertidor.ModeloData;
import ObjConvertidor.ObjArchivoCargador;
import RenderEngine.MaestroRenderizador;
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
        
        //Modelo Jugador
        ModeloData dataJugador = ObjArchivoCargador.cargarOBJ("Player");
        ModeloTexturizado modelPlayer = new ModeloTexturizado(loader.cargarToVAO(dataJugador.getVertices(), dataJugador.getTexturaCordenadas(),
                dataJugador.getNormales(), dataJugador.getIndices()),new ModelTexture(loader.cargarTextura("PlayerTexture")) );
        Jugador player = new Jugador(modelPlayer, new Vector3f(0,0,-50), 0, 0, 0, 4);
        
        //Modelo del monstruo
        ModeloData dataMons = ObjArchivoCargador.cargarOBJ("Monstruo");
        ModeloTexturizado modelMosntruo = new ModeloTexturizado( loader.cargarToVAO(dataMons.getVertices(), dataMons.getTexturaCordenadas(), 
                dataMons.getNormales(), dataMons.getIndices()),    new ModelTexture(loader.cargarTextura("MonsTexture")) );
        Monstruo monster = new Monstruo(modelMosntruo, new Vector3f(-800, 15, -800), 0, 0, 0, 4);
        
        //Modelo de los Muros
        ModeloData data =  ObjArchivoCargador.cargarOBJ("murobasico");
        ModeloRaw ArbolModelo = loader.cargarToVAO(data.getVertices(), data.getTexturaCordenadas(), data.getNormales(), data.getIndices());
        ModeloTexturizado staticModel = new ModeloTexturizado(ArbolModelo, new ModelTexture( loader.cargarTextura("MuroTexture") ));
        
        //Carga de Entidades
        List<Entidad> entidades = new ArrayList<Entidad>();
        Random ran = new Random();
        for(int i=0;i<150;i++){
            entidades.add(new Entidad(staticModel,new Vector3f(ran.nextFloat()*1600 - 800, 0,ran.nextFloat()*-800),0,ran.nextFloat()*90,0,5));
        }
        
        //Creacion de Luz
        Luz luz = new Luz(new Vector3f(0,20000, -400), new Vector3f(1, 1, 1));  //Posicion de la luz y su color
        
        Terreno terreno = new Terreno(0, 0,loader,new ModelTexture(loader.cargarTextura("grass")));
        Terreno terreno2 = new Terreno(1, 0,loader,new ModelTexture(loader.cargarTextura("grass")));
        
        Camara camara = new Camara(new Vector3f(0, 0, -10));
        
        MaestroRenderizador renderer = new MaestroRenderizador();
        while(!Display.isCloseRequested()){
            //Logica del Juego
            camara.Mover();
            luz.setPosicion(camara.getPosicion());
            player.moverse();
            
            //Renderizado
            renderer.procesarEntidad(player);
            renderer.procesarEntidad(monster);
            
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
