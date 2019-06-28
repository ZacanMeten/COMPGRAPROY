package Sombreadores;

import Entidades.Camara;
import Entidades.Luz;
import Utilidades.Matematicas;
import org.lwjgl.util.vector.Matrix4f;

public class TerrenoShader extends ShaderProgram{
	
	
	private static final String VERTEX_FILE = "src/Sombreadores/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/Sombreadores/terrainFragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;

	public TerrenoShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void enlazarAtributos() {
		super.enlazarAtributo(0, "position");
		super.enlazarAtributo(1, "textureCoordinates");
		super.enlazarAtributo(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		
	}
	
	public void cargarVariablesBrillo(float damper,float reflectivity){
		super.cargarFloat(location_shineDamper, damper);
		super.cargarFloat(location_reflectivity, reflectivity);
	}
	
	public void cargarTransformacionMatriz(Matrix4f matrix){
		super.cargarMatriz(location_transformationMatrix, matrix);
	}
	
	public void cargarLuz(Luz light){
		super.cargarVector(location_lightPosition, light.getPosicion());
		super.cargarVector(location_lightColour, light.getColor());
	}
	
	public void cargarMatrizVista(Camara camera){
		Matrix4f viewMatrix = Matematicas.crearMatrizVista(camera);
		super.cargarMatriz(location_viewMatrix, viewMatrix);
	}
	
	public void cargarProyeccionMatriz(Matrix4f projection){
		super.cargarMatriz(location_projectionMatrix, projection);
	}
	

}
