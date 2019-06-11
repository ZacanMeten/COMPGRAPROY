/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderEngine;

import Modelos.ModeloRaw;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author reant
 */
public class Cargador {
    
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    
    private List<Integer> texturas = new ArrayList<Integer>();
    
    /* Crea una VAO y al almacena los datos de posicion de los vertices en el atributo 0 de la VAO
      @parametro posiciones
      @return el modelo cargado  
    */
    
    public ModeloRaw cargarToVAO(float[] posiciones,float[] CoordenadasTextura, float[] normales, int[] indices){
        int vaoID = crearVAO();
        
        enlazarIndiceBuffer(indices);
        almacenarDataEnListaAtributos(0, 3, posiciones);        
        almacenarDataEnListaAtributos(1, 2, CoordenadasTextura);
        almacenarDataEnListaAtributos(2, 3, normales);
        desligarVAO();
        return new ModeloRaw(vaoID, indices.length);
    }
    
    //Implementacion para Texturas
    public int cargarTextura(String nombreArchivo){
        Texture textura = null;
        try {
            textura = TextureLoader.getTexture("PNG", new FileInputStream("res/"+nombreArchivo+".png") );
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        int TextureID = textura.getTextureID(); //Recupera el id de la textura cargada
        texturas.add(TextureID);    //Ingresa el id de la textura cargada en la lista texturas
        return TextureID;
    }
    
    /* Borra todas la VAOs y VBOs cuando el juego este cerrado. VAOs Y VBOs estan localizados en la memoria de video*/
    public void Limpieza(){
        for( int vao : vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo : vbos){
            GL15.glDeleteBuffers(vbo);
        }
        for(int tex : texturas){
            GL11.glDeleteTextures(tex);
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
    private void almacenarDataEnListaAtributos(int numeroAtributo, int tamañoCoordenada, float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = almacenarDataEnFloatBuffer(data);
	GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	GL20.glVertexAttribPointer(numeroAtributo, tamañoCoordenada, GL11.GL_FLOAT, false, 0, 0);
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    private FloatBuffer almacenarDataEnFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
	buffer.put(data);
	buffer.flip();
	return buffer;
    }
    
    //IndexBuffer
    private void enlazarIndiceBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = almacenarDataEnIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }
    private IntBuffer almacenarDataEnIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
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
