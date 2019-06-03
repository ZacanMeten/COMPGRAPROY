/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Display;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author reant
 */
public class Renderizador {
    
    public void preparar(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(1, 0, 0, 1); //RGB Alpha
    }
    
    public void render(ModeloRaw modelo){
        GL30.glBindVertexArray(modelo.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, modelo.getVerticeCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}
