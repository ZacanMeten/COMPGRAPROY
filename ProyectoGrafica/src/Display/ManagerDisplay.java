/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Display;

import org.lwjgl.LWJGLException;
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
        
    public static void crearDisplay(){
        
        ContextAttribs attribs = new ContextAttribs(3,2);
        attribs.withForwardCompatible(true);
        attribs.withProfileCore(true);        
        
        try{        
            Display.setDisplayMode(new DisplayMode(Ancho, Alto));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("BuscaLetras Prueba");
        } catch(LWJGLException e){
            e.printStackTrace();;
        }
        
        GL11.glViewport(0, 0, Ancho, Alto);
    }
    public static void actualizarDisplay(){
        Display.sync(FPS_cap);
        Display.update();
    }
    public static void cerrarDisplay(){
        Display.destroy();
    }
}
