/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjConvertidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class ObjArchivoCargador {
    private static final String RES_Loc = "res/";
    
    public static ModeloData cargarOBJ(String objNombre){
        FileReader isr = null;
        File objArchivo = new File(RES_Loc+ objNombre +".obj");
        try {
            isr = new FileReader(objArchivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjArchivoCargador.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader lector = new BufferedReader(isr);
        String linea;
        List<Vertice> vertices = new ArrayList<Vertice>();
        List<Vector2f> texturas = new ArrayList<Vector2f>();
        List<Vector3f> normales = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();
        try {
            while(true){
                linea = lector.readLine();
                if(linea.startsWith("v ")) {
                    String[] actualLinea = linea.split(" ");
                    Vector3f vertex = new Vector3f( (float) Float.valueOf(actualLinea[1]), 
                            (float)Float.valueOf(actualLinea[2]), 
                            (float)Float.valueOf(actualLinea[3]) );
                    Vertice newVertice = new Vertice(vertices.size(), vertex);
                    vertices.add(newVertice);
                } else if(linea.startsWith("vt ")){
                    String[] actualLinea = linea.split(" ");
                    Vector2f textura = new Vector2f((float) Float.valueOf(actualLinea[1]),
                            (float) Float.valueOf(actualLinea[2]));
                    texturas.add(textura);
                } else if(linea.startsWith("vn ")){
                    String[] acLinea = linea.split(" ");
                    Vector3f normal = new Vector3f((float) Float.valueOf(acLinea[1]),
                            (float) Float.valueOf(acLinea[2]),
                            (float) Float.valueOf(acLinea[3]));
                    normales.add(normal);
                }else if(linea.startsWith("f ")){
                    break;
                }
            }
            while(linea != null && linea.startsWith("f ")){
                String[] actualLinea = linea.split(" ");                
                String[] vertex1 = actualLinea[1].split("/");
                String[] vertex2 = actualLinea[2].split("/");
                String[] vertex3 = actualLinea[3].split("/");
                procesarVertice(vertex1, vertices, indices);
                procesarVertice(vertex2, vertices, indices);
                procesarVertice(vertex3, vertices, indices);
                linea = lector.readLine();
            }
            lector.close();  
        } catch (IOException e) {
            System.err.println("Error leer el archivo" + objNombre);
        }
        removerVerticesNoUsados(vertices);
        float[] verticesArray = new float[vertices.size() * 3];
        float[] texturasArray = new float[vertices.size() * 2];
        float[] normalesArray = new float[vertices.size() * 3];
        float extremo = convertirDatos_Array(vertices,texturas,normales, verticesArray,texturasArray,normalesArray);
        int[] indicesArray  = convertirIndicesListaToArray(indices);
        ModeloData data = new ModeloData(verticesArray, texturasArray, normalesArray, indicesArray, extremo);
        return data;
    }
    
    private static void procesarVertice(String[] vertex, List<Vertice> vertices, List<Integer> indices) {
        int index = Integer.parseInt(vertex[0]) - 1;
        Vertice currentVertex = vertices.get(index);
        int textureIndex = Integer.parseInt(vertex[1]) - 1;
        int normalIndex = Integer.parseInt(vertex[2]) - 1;
        if (!currentVertex.isSet()) {
            currentVertex.setTexturaIndice(textureIndex);
            currentVertex.setNormalIndice(normalIndex);
            indices.add(index);
        } else {
            tratar_con_vertices_ya_procesados(currentVertex, textureIndex, normalIndex, indices,
                    vertices);
        }
    }
    
    private static int[] convertirIndicesListaToArray(List<Integer> indices) {
        int[] indicesArray = new int[indices.size()];
        for (int i = 0; i < indicesArray.length; i++) {
            indicesArray[i] = indices.get(i);
        }
        return indicesArray;
    }
    private static float convertirDatos_Array(List<Vertice> vertices, List<Vector2f> textures,
            List<Vector3f> normals, float[] verticesArray, float[] texturesArray,
            float[] normalsArray) {
        float furthestPoint = 0;
        for (int i = 0; i < vertices.size(); i++) {
            Vertice currentVertex = vertices.get(i);
            if (currentVertex.getLongitud()> furthestPoint) {
                furthestPoint = currentVertex.getLongitud();
            }
            Vector3f position = currentVertex.getPosicion();
            Vector2f textureCoord = textures.get(currentVertex.getTexturaIndice());
            Vector3f normalVector = normals.get(currentVertex.getNormalIndice());
            verticesArray[i * 3] = position.x;
            verticesArray[i * 3 + 1] = position.y;
            verticesArray[i * 3 + 2] = position.z;
            texturesArray[i * 2] = textureCoord.x;
            texturesArray[i * 2 + 1] = 1 - textureCoord.y;
            normalsArray[i * 3] = normalVector.x;
            normalsArray[i * 3 + 1] = normalVector.y;
            normalsArray[i * 3 + 2] = normalVector.z;
        }
        return furthestPoint;
    }
    
    private static void tratar_con_vertices_ya_procesados(Vertice previoVertice, int newTextureIndex,
            int newNormalIndex, List<Integer> indices, List<Vertice> vertices) {
        if (previoVertice.hasMismaTexturaYNormal(newTextureIndex, newNormalIndex)) {
            indices.add(previoVertice.getIndice());
        } else {
            Vertice otroVertice = previoVertice.getDobleVertice();
            if (otroVertice != null) {
                tratar_con_vertices_ya_procesados(otroVertice, newTextureIndex, newNormalIndex,
                        indices, vertices);
            } else {
                Vertice duplicadoVertice = new Vertice(vertices.size(), previoVertice.getPosicion());
                duplicadoVertice.setTexturaIndice(newTextureIndex);
                duplicadoVertice.setNormalIndice(newNormalIndex);
                previoVertice.setDobleVertice(duplicadoVertice);
                vertices.add(duplicadoVertice);
                indices.add(duplicadoVertice.getIndice());
            } 
        }
    }
    
    private static void removerVerticesNoUsados(List<Vertice> vertices){
        for(Vertice ver : vertices){
            if(!ver.isSet()){
                ver.setTexturaIndice(0);
                ver.setNormalIndice(0);
            }
        }
    }
}
