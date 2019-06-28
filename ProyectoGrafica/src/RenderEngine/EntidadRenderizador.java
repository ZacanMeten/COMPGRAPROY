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
import java.util.List;
import java.util.Map;
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
public class EntidadRenderizador {
    
    private StaticShader shader;
    
    public EntidadRenderizador(StaticShader shader, Matrix4f proyeccionMatriz){
        this.shader = shader;
        shader.empezar();
        shader.cargarProjectionMatrix(proyeccionMatriz);
        shader.parar();
    }
    

    
    public void render(Map<ModeloTexturizado,List<Entidad>> entidades){
        for(ModeloTexturizado  model : entidades.keySet()){
            prepararModeloTexturizado(model);
            List<Entidad> lote = entidades.get(model);
            for(Entidad entity : lote){
                prepararInstancia(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawmodel().getVerticeCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            desenlazarModeloTexturizado(); //Anted del GL11 drawElements
        }
    }
    
    public void prepararModeloTexturizado(ModeloTexturizado modelo){
        ModeloRaw modeloR = modelo.getRawmodel();
        GL30.glBindVertexArray(modeloR.getVaoID());
        //Habilita las VAO
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        //Antes de renderizar algo se tiene  que cargar los valores de reflectividad y brillo
        ModelTexture textura = modelo.getTexture();
        if(textura.isHasTransparencia()){
            MaestroRenderizador.deshabilitarEntresacar();
        }
        shader.cargarLuzFalsaVariable(textura.isUsarLuzFalsa());
        shader.cargarBrilloVariable(textura.getBrilloDamper(), textura.getReflectividad());
        
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, modelo.getTexture().getTexturaID());
    }
    
    public void desenlazarModeloTexturizado(){
        MaestroRenderizador.habilitarEntresacar();
        //Termina con el uso de las VAO
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
    
    public void prepararInstancia(Entidad entidad){
        Matrix4f matrizTransformacion = Matematicas.crearTransformationMatrix(entidad.getPosicion(), entidad.getRotX(), entidad.getRotY(), entidad.getRotZ(), entidad.getEscala());
        shader.cargarTransformationMatrix(matrizTransformacion);
    }
    
}