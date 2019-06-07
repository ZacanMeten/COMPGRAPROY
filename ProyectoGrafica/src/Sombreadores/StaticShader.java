/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sombreadores;

import Entidades.Camara;
import Utilidades.Matematicas;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author reant
 */
public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "src/Sombreadores/Vertices-Shaders.txt";
    private static final String FRAGMENT_FILE = "src/Sombreadores/Fragmento-Shaders.txt";
    
    private int localizacion_transformationMatrix;
    private int localizacion_projectionMatrix;
    private int localizacion_viewMatrix;
    
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void enlazarAtributos() {
        super.enlazarAtributo(0, "position");
        super.enlazarAtributo(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        localizacion_transformationMatrix = super.getUniformLocation("transformationMatrix");
        localizacion_projectionMatrix = super.getUniformLocation("projectionMatrix");
        localizacion_viewMatrix = super.getUniformLocation("viewMatrix");
    }
    
    public void cargarTransformationMatrix(Matrix4f matriz){
        super.cargarMatriz(localizacion_transformationMatrix, matriz);
    }
    public void cargarViewMatrix(Camara camara){
        Matrix4f matriz = Matematicas.crearMatrizVista(camara);
        super.cargarMatriz(localizacion_viewMatrix, matriz);
    }
    public void cargarProjectionMatrix(Matrix4f proyeccion){
        super.cargarMatriz(localizacion_projectionMatrix, proyeccion);
    }
    
}
