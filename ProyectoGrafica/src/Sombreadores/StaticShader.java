/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sombreadores;

import Entidades.Camara;
import Entidades.Luz;
import Utilidades.Matematicas;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

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
    private int localizacion_lightPosition;
    private int localizacion_lightColour;
    private int localizacion_shineDamper;
    private int localizacion_reflectivity;
    private int localizacion_useFakeLighting;
    private int localizacion_skyColour;
    
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void enlazarAtributos() {
        super.enlazarAtributo(0, "position");
        super.enlazarAtributo(1, "textureCoords");
        super.enlazarAtributo(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        localizacion_transformationMatrix = super.getUniformLocation("transformationMatrix");
        localizacion_projectionMatrix = super.getUniformLocation("projectionMatrix");
        localizacion_viewMatrix = super.getUniformLocation("viewMatrix");
        localizacion_lightPosition = super.getUniformLocation("lightPosition");
        localizacion_lightColour = super.getUniformLocation("lightColour");
        localizacion_shineDamper = super.getUniformLocation("shineDamper");
        localizacion_reflectivity = super.getUniformLocation("reflectivity");
        localizacion_useFakeLighting = super.getUniformLocation("useFakeLighting");
        localizacion_skyColour = super.getUniformLocation("skyColour");
    }
    
    public void cargarColorCielo(float r, float g, float b){
        super.cargarVector(localizacion_skyColour, new Vector3f(r,g,b));
    }
    
    public void cargarLuzFalsaVariable(boolean useFake){
        super.cargarBoolean(localizacion_useFakeLighting, useFake);
    }
    
    public void cargarBrilloVariable(float damper, float reflectivity){
        super.cargarFloat(localizacion_shineDamper, damper);
        super.cargarFloat(localizacion_reflectivity, reflectivity);
    }
    
    public void cargarTransformationMatrix(Matrix4f matriz){
        super.cargarMatriz(localizacion_transformationMatrix, matriz);
    }
    
    public void cargarLuz(Luz luz){
        super.cargarVector(localizacion_lightPosition, luz.getPosicion());
        super.cargarVector(localizacion_lightColour, luz.getColor());
    }
    
    public void cargarViewMatrix(Camara camara){
        Matrix4f matriz = Matematicas.crearMatrizVista(camara);
        super.cargarMatriz(localizacion_viewMatrix, matriz);
    }
    public void cargarProjectionMatrix(Matrix4f proyeccion){
        super.cargarMatriz(localizacion_projectionMatrix, proyeccion);
    }
    
}
