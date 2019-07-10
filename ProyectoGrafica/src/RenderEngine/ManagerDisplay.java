/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;


/**
 *
 * @author reant
 */
public class ManagerDisplay {
    
    private static final int Ancho = 1280;
    private static final int Alto = 720;
    private static final int FPS_cap = 120;
    
    //Cuanto tiempo toma un frame a renderizar
    public static long ultimoTiempoFrame;
    public static float delta;
        
    public static void crearDisplay(){        
        ContextAttribs attribs = new ContextAttribs(3,2);
        attribs.withForwardCompatible(true);
        attribs.withProfileCore(true);
        
        try{
            Display.setDisplayMode(new DisplayMode(Ancho, Alto));
            Display.create(new PixelFormat(), attribs);
            Display.setLocation(0, 0);
            Display.setTitle("BuscaLetras Prueba");
        } catch(LWJGLException e){
            e.printStackTrace();;
        }
        
        GL11.glViewport(0, 0, Ancho, Alto);
        ultimoTiempoFrame = getTiempoActual();
    }
    public static void actualizarDisplay(){
        Display.sync(FPS_cap);
        Display.update();
        long actualFrameTime = getTiempoActual();
        delta = (actualFrameTime - ultimoTiempoFrame)/1000f; //Dividir en 1000 transforma los mseg a seg
        ultimoTiempoFrame = actualFrameTime;
    }
    public static void cerrarDisplay(){
        Display.destroy();
    }
    
    public static float getTiempoFrameSegundos(){
        return delta;
    }
    
    private static long getTiempoActual(){
        //Retorno del tiempo en milisegundos
        return Sys.getTime()*1000 /Sys.getTimerResolution();
    }
}
