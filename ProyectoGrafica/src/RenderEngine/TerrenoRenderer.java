/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderEngine;

import Entidades.Entidad;
import Modelos.ModeloRaw;
import Modelos.ModeloTexturizado;
import Sombreadores.TerrenoShader;
import Terrenos.Terreno;
import Texturas.ModelTexture;
import Utilidades.Matematicas;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class TerrenoRenderer {
    private TerrenoShader shader;
    
    public TerrenoRenderer(TerrenoShader shader, Matrix4f proyeccionMatriz){
        this.shader = shader;
        shader.empezar();
        shader.cargarProyeccionMatriz(proyeccionMatriz);
        shader.parar();        
    }
    
    public void render(List<Terreno> terrenos){
        for(Terreno terr : terrenos){
            prepararTerreno(terr);
            cargarModeloMatriz(terr);
            GL11.glDrawElements(GL11.GL_TRIANGLES, terr.getModelo().getVerticeCount(), GL11.GL_UNSIGNED_INT, 0);
            desenlazarModeloTexturizado();
        }
    }
    
        public void prepararTerreno(Terreno terreno){
        ModeloRaw modeloR = terreno.getModelo();
        GL30.glBindVertexArray(modeloR.getVaoID());
        //Habilita las VAO
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        //Antes de renderizar algo se tiene  que cargar los valores de reflectividad y brillo
        ModelTexture textura = terreno.getTextura();
        shader.cargarVariablesBrillo(textura.getBrilloDamper(), textura.getReflectividad());
        
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textura.getTexturaID() );
    }
    
    public void desenlazarModeloTexturizado(){
        //Termina con el uso de las VAO
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
    
    public void cargarModeloMatriz(Terreno terrain){
        Matrix4f matrizTransformacion = Matematicas.crearTransformationMatrix(new Vector3f(terrain.getX(),0,terrain.getZ()) , 0, 0, 0, 1);
        shader.cargarTransformacionMatriz(matrizTransformacion);
    }
    
}
