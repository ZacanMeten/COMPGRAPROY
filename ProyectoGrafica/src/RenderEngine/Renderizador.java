/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderEngine;

import Entidades.Entidad;
import Modelos.ModeloRaw;
import Modelos.ModeloTexturizado;
import Sombreadores.StaticShader;
import Texturas.ModelTexture;
import Utilidades.Matematicas;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author reant
 */
public class Renderizador {
    
    private static final float FOV = 70;
    private static final float near_Plane = 0.1f;  //Plano de cerca
    private static final float far_Plane = 1000;  //Plano de lejos
     
    private Matrix4f MatrizProyeccion;
    
    public Renderizador(StaticShader shader){
        crearMatrizProyeccion();
        shader.empezar();
        shader.cargarProjectionMatrix(MatrizProyeccion);
        shader.parar();
    }
    
    public void preparar(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.5f, 0.5f, 0.5f, 1); //RGB Alpha - COLOR DE FONDO
    }
    
    public void render(Entidad entidad, StaticShader shader){
        ModeloTexturizado modelo = entidad.getModelo();
        ModeloRaw modeloR = modelo.getRawmodel();
        GL30.glBindVertexArray(modeloR.getVaoID());
        //Habilita las VAO
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        
        Matrix4f matrizTransformacion = Matematicas.crearTransformationMatrix(entidad.getPosicion(), entidad.getRotX(), entidad.getRotY(), entidad.getRotZ(), entidad.getEscala());
        shader.cargarTransformationMatrix(matrizTransformacion);
        
        //Antes de renderizar algo se tiene  que cargar los valores de reflectividad y brillo
        ModelTexture textura = modelo.getTexture();
        shader.cargarBrilloVariable(textura.getBrilloDamper(), textura.getReflectividad());
        
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, modelo.getTexture().getTexturaID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, modeloR.getVerticeCount(), GL11.GL_UNSIGNED_INT, 0);
        
        //Termina con el uso de las VAO
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
    
    public void crearMatrizProyeccion(){
        float aspectRadio = (float) Display.getWidth() / (float) Display.getHeight();        
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRadio);
        float x_scale = y_scale / aspectRadio;
        float frustum_length = far_Plane - near_Plane;
 
        MatrizProyeccion = new Matrix4f();
        MatrizProyeccion.m00 = x_scale;
        MatrizProyeccion.m11 = y_scale;
        MatrizProyeccion.m22 = -((far_Plane + near_Plane) / frustum_length);
        MatrizProyeccion.m23 = -1;
        MatrizProyeccion.m32 = -((2 * near_Plane * far_Plane) / frustum_length);
        MatrizProyeccion.m33 = 0;
    }
}