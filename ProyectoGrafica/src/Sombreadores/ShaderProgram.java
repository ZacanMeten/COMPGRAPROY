/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sombreadores;

import com.sun.media.jfxmedia.AudioClip;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public abstract class ShaderProgram {
    private int programID;
    private int verticeShaderID;
    private int fragmentoShaderID;
    
    private static FloatBuffer matrizBuffer  = BufferUtils.createFloatBuffer(16); // Matriz 4x4
    
    public ShaderProgram(String verticeFile, String framentFile) {
        this.verticeShaderID = cargarShader(verticeFile, GL20.GL_VERTEX_SHADER);
        this.fragmentoShaderID = cargarShader(framentFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, verticeShaderID);
        GL20.glAttachShader(programID, fragmentoShaderID);
        enlazarAtributos();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
    }
    
    protected abstract void getAllUniformLocations();
    
    protected  int getUniformLocation(String nombreUniforme){
        return GL20.glGetUniformLocation(programID, nombreUniforme);
    } 
    
    public void empezar(){
        GL20.glUseProgram(programID);
    }
    public void parar(){
        GL20.glUseProgram(0);
    }
    public void limpiar(){
        parar();
        GL20.glDetachShader(programID, verticeShaderID);
        GL20.glDetachShader(programID, fragmentoShaderID);
        GL20.glDeleteShader(verticeShaderID);
        GL20.glDeleteShader(fragmentoShaderID);
        GL20.glDeleteProgram(programID);
    }
    
    protected abstract void enlazarAtributos();
    
    protected void enlazarAtributo(int atributo, String nombreVariable){
        GL20.glBindAttribLocation(programID, atributo, nombreVariable);
    }
    
    protected void cargarFloat (int localizacion, float valor){
        GL20.glUniform1f(localizacion, valor);
    }
    
    protected void loadVector (int localizacion, Vector3f vector){
        GL20.glUniform3f(localizacion,  vector.x, vector.y, vector.z);
    }
    
    protected void cargarBoolean(int localizacion, boolean  valor){
        float aCargar = 0;
        if(valor){
            aCargar = 1;
        }
        GL20.glUniform1f(localizacion, aCargar);
    }
    
    protected void cargarMatriz(int localizacion, Matrix4f matriz){
        matriz.store(matrizBuffer);
        matrizBuffer.flip();
        GL20.glUniformMatrix4(localizacion, false,  matrizBuffer);
    }
    
    private static int cargarShader(String archivo, int tipo){
        StringBuilder OrigenShader = new StringBuilder();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            while( (linea = lector.readLine()) != null){
                OrigenShader.append(linea).append("\n");
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo");
            e.printStackTrace();
            System.exit(-1);
        }
        
        int ShaderID = GL20.glCreateShader(tipo);
        GL20.glShaderSource(ShaderID, OrigenShader);
        GL20.glCompileShader(ShaderID);
        
        if( GL20.glGetShaderi(ShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE ){
            System.out.println(GL20.glGetShaderInfoLog(ShaderID, 500));
            System.err.println("No se pudo compilar el shader");
            System.exit(-1);
        }
        
        return ShaderID;
    }
}
