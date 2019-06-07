/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderEngine;

import Modelos.ModeloRaw;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class OBJcargador {

    public static ModeloRaw cargarOBJmodel(String nombreFile, Cargador loader) {
        FileReader fr = null;
        
        try {
            fr = new FileReader(new File("res/"+nombreFile+".obj"));
        } catch (FileNotFoundException ex) {
            System.out.println("No se pudo cargar el achivo obj " + nombreFile);
            ex.printStackTrace();
        }
        
        BufferedReader lector = new BufferedReader(fr);
        String linea;
        List<Integer>   indices = new ArrayList<Integer>();
        List<Vector3f>  vertices = new ArrayList<Vector3f>();
        List<Vector2f>  texturas = new ArrayList<Vector2f>();
        List<Vector3f>  normals = new ArrayList<Vector3f>();
        
        float[] verticesArray = null;
        float[] texturasArray = null;
        float[] normalsArray = null;
        int[] indicesArray = null;
        
        try {
            while(true){
                linea = lector.readLine();
                String[] currentLinea  = linea.split(" ");
                if(linea.startsWith("v ")){
                    Vector3f vertice = new Vector3f(Float.parseFloat(currentLinea[1]) , Float.parseFloat(currentLinea[2])  , Float.parseFloat(currentLinea[3]) );
                    vertices.add(vertice);
                }else if(linea.startsWith("vt ")){
                    Vector2f textura = new Vector2f(Float.parseFloat(currentLinea[1]) , Float.parseFloat(currentLinea[2])  );
                    texturas.add(textura);
                }else if(linea.startsWith("vn ")){
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLinea[1]) , Float.parseFloat(currentLinea[2])  , Float.parseFloat(currentLinea[3]) );
                    normals.add(normal);
                }else if(linea.startsWith("f ")){
                    texturasArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    break;
                }
            }
            while(linea!=null){
                if( !linea.startsWith("f ") ){
                    linea = lector.readLine();
                    continue;
                }
                String[] currentLinea = linea.split(" ");
                String[] vertice1 = currentLinea[1].split("/");
                String[] vertice2 = currentLinea[2].split("/");
                String[] vertice3 = currentLinea[3].split("/");
                
                procesarVertice(vertice1, indices, texturas, normals, texturasArray, normalsArray);
                procesarVertice(vertice2, indices, texturas, normals, texturasArray, normalsArray);
                procesarVertice(vertice3, indices, texturas, normals, texturasArray, normalsArray);
                
                linea = lector.readLine();
            }
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        verticesArray = new float[vertices.size() * 3];
        indicesArray = new int[indices.size()];
        
        int verticePuntero = 0;
        for(Vector3f vertex : vertices){
            verticesArray[verticePuntero++] = vertex.x;
            verticesArray[verticePuntero++] = vertex.y;
            verticesArray[verticePuntero++] = vertex.z;
        }
        
        for(int i=0 ;i < indices.size();i++){
            indicesArray[i] = indices.get(i);
        }
        
        return loader.cargarToVAO(verticesArray, texturasArray, indicesArray);
    }
    
    public static void procesarVertice(String[] verticeData, List<Integer> indices, List<Vector2f> texturas, List<Vector3f> normals, float[] texturaArray, float[] normalsArray){
        int currentVerticePuntero = Integer.parseInt( verticeData[0]) - 1 ;
        indices.add(currentVerticePuntero);
        
        Vector2f currentTexto = texturas.get( Integer.parseInt(verticeData[1]) -1 );
        texturaArray[currentVerticePuntero*2] = currentTexto.x;
        texturaArray[currentVerticePuntero*2 + 1] = 1 - currentTexto.y;
        
        Vector3f currentNorm = normals.get( Integer.parseInt( verticeData[2]) - 1);
        normalsArray[currentVerticePuntero*3] = currentNorm.x;
        normalsArray[currentVerticePuntero*3 + 1] = currentNorm.y;
        normalsArray[currentVerticePuntero*3 + 2] = currentNorm.z;
        
    }
    
}
