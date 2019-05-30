/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Display;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author reant
 */
public class Cargador {
    
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    
    /* Crea una VAO y al almacena los datos de posicion de los vertices en el atributo 0 de la VAO
      @parametro posiciones
      @return el modelo cargado  
    */
    
    public ModeloRaw cargarToVAO(float[] posiciones){
        int vaoID = crearVAO();
        almacenarDataEnListaAtributos(0, posiciones);
        desligarVAO();
        return new ModeloRaw(vaoID, posiciones.length / 3); //cada vertex tiene 3 atributos
    }
    
    /* Borra todas la VAOs y VBOs cuando el juego este cerrado. VAOs Y VBOs estan localizados en la memoria de video*/
    public void Limpieza(){
        for( int vao : vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo : vbos){
            GL15.glDeleteBuffers(vbo);
        }
    }
    
    /* Crea un nuevo VAO y devuelve su ID.
    * Un VAO contiene datos de geometría que podemos representar y se almacena físicamente en la memoria de la GPU, 
    * de modo que se puede acceder a ellos muy rápidamente durante el procesamiento.
    
    * Como la mayoria de objetos en OpenGL, la nueva VAO es creada usando un metodo "gen"
    * que retorna el ID de la nueva VAO. A fin de usar la VAO, esta necesita ser una VAO activa.
    * Solo se puede puede estar activa una VAO a la vez.
    * Para hacer esta VAO la VAO activa, necesitamos enlazarla 
    
    @return El ID de la nueva creada VAO
    */
    private int crearVAO(){
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;        
    }
    
    /* Almacena los datos de posición de los vértices en el atributo 0 del VAO.
    * 
    * 
    */
    private void almacenarDataEnListaAtributos(int numeroAtributo, float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = almacenarDataEnFloatBuffer(data);
	GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	GL20.glVertexAttribPointer(numeroAtributo, 3, GL11.GL_FLOAT, false, 0, 0);
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    private FloatBuffer almacenarDataEnFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
	buffer.put(data);
	buffer.flip();
	return buffer;
    }
    
    /* Desliga el VAO despues que hayamos terminado de usarlo. Si queremos usarlo o editarlo
    * la VAO debe ser ligada otra vez.
    */
    private void desligarVAO(){
        GL30.glBindVertexArray(0);
    }
}
