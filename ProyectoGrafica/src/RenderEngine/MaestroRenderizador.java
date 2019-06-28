/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderEngine;

import Entidades.Camara;
import Entidades.Entidad;
import Entidades.Luz;
import Modelos.ModeloTexturizado;
import Sombreadores.StaticShader;
import Sombreadores.TerrenoShader;
import Terrenos.Terreno;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class MaestroRenderizador {
    
    private static final float FOV = 70;
    private static final float near_Plane = 0.1f;  //Plano de cerca
    private static final float far_Plane = 1000;  //Plano de lejos
    
    private Matrix4f proyeccionMatriz;
    
    private StaticShader shader = new StaticShader();
    private EntidadRenderizador render;
    
    private TerrenoRenderer terrenoRenderer;
    private TerrenoShader terrenoShader = new TerrenoShader();
    
    //Empaquedado de los objetos que tendran texturas y Entidad
    private Map<ModeloTexturizado,List<Entidad>> entidades = new HashMap<ModeloTexturizado, List<Entidad>>();
    //Lista de Terrenos
    private List<Terreno> terrenos = new ArrayList<Terreno>();
    
    
    public MaestroRenderizador(){
        habilitarEntresacar();
        crearMatrizProyeccion();
        render = new EntidadRenderizador(shader, proyeccionMatriz);
        terrenoRenderer = new TerrenoRenderer(terrenoShader, proyeccionMatriz);
    }
    
    public static void habilitarEntresacar(){
        GL11.glEnable(GL11.GL_CULL_FACE);   //Optimizar el renderizado no renderizando ambas caras de un objeto(dentro y fuera)
        GL11.glCullFace(GL11.GL_BACK);      //Que cara no se renderizara
    }
    public static void deshabilitarEntresacar(){
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
    
    public void renderizar(Luz sol, Camara camera){
        preparar(); 
        shader.empezar();
        shader.cargarLuz(sol);
        shader.cargarViewMatrix(camera);
        render.render(entidades);
        shader.parar();
        terrenoShader.empezar();
        //Posicion Defecto de la luz sobre el terreno
        Vector3f pos = new Vector3f(0,3500,-400);
        terrenoShader.cargarLuz(new Luz( pos, new Vector3f(1, 1, 1)));
        terrenoShader.cargarMatrizVista(camera);
        terrenoRenderer.render(terrenos);
        terrenoShader.parar();
        //Importante limpiar para que ubicara uno sobre otro y  los FPS caeran
        terrenos.clear();
        entidades.clear();
    }
    
    public void procesarTerreno(Terreno terreno){
        terrenos.add(terreno);
    }
    
    public void procesarEntidad(Entidad entidad){
        ModeloTexturizado modeloEntidad = entidad.getModelo();
        List<Entidad> lote = entidades.get(modeloEntidad);
        if(lote != null){
            lote.add(entidad);
        }else{
            List<Entidad> nuevoLote = new ArrayList<Entidad>();
            nuevoLote.add(entidad);
            entidades.put(modeloEntidad, nuevoLote);
        }
    }
    
    public void limpiar(){
        shader.limpiar();
        terrenoShader.limpiar();
    }
    
    public void preparar(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.5f, 0.5f, 0.5f, 1); //RGB Alpha - COLOR DE FONDO
    }
    
    public void crearMatrizProyeccion(){
        float aspectRadio = (float) Display.getWidth() / (float) Display.getHeight();        
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRadio);
        float x_scale = y_scale / aspectRadio;
        float frustum_length = far_Plane - near_Plane;
 
        proyeccionMatriz = new Matrix4f();
        proyeccionMatriz.m00 = x_scale;
        proyeccionMatriz.m11 = y_scale;
        proyeccionMatriz.m22 = -((far_Plane + near_Plane) / frustum_length);
        proyeccionMatriz.m23 = -1;
        proyeccionMatriz.m32 = -((2 * near_Plane * far_Plane) / frustum_length);
        proyeccionMatriz.m33 = 0;
    }
}
