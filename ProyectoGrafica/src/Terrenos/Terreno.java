/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrenos;

import Modelos.ModeloRaw;
import RenderEngine.Cargador;
import Texturas.ModelTexture;

/**
 *
 * @author reant
 */
public class Terreno {
    private static final float SIZE = 800; //Tamaño por cuadro de terreno generado
    private static final int Vertice_Cuenta = 50;
    
    private float x;
    private float z;
    private ModeloRaw modelo;
    private ModelTexture textura;
    
    //Cuad = Cuadricula
    public Terreno(int cuadX, int cuadZ,Cargador loader, ModelTexture texture){
        this.textura = texture;
        this.x = -cuadX * SIZE;
        this.z = cuadZ * SIZE;
        this.modelo = generarTerreno(loader);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public ModeloRaw getModelo() {
        return modelo;
    }

    public void setModelo(ModeloRaw modelo) {
        this.modelo = modelo;
    }

    public ModelTexture getTextura() {
        return textura;
    }

    public void setTextura(ModelTexture textura) {
        this.textura = textura;
    }

    
    private ModeloRaw generarTerreno(Cargador loader){
        int count = Vertice_Cuenta * Vertice_Cuenta;
        float[] vertices = new float[count * 3];
        float[] normales = new float[count * 3];
        float[] texturaCoordenadas = new float[count*2];
        int[] indices = new int[6*(Vertice_Cuenta-1)*(Vertice_Cuenta-1)];
        int verticePuntero = 0;
        for(int i=0;i<Vertice_Cuenta;i++){
            for(int j=0;j<Vertice_Cuenta;j++){
                vertices[verticePuntero*3] =    (float)j/((float)Vertice_Cuenta - 1) * SIZE;
                vertices[verticePuntero*3+1] =  0;
                vertices[verticePuntero*3+2] =  -SIZE+(float)i/((float)Vertice_Cuenta - 1) * SIZE;
                normales[verticePuntero*3] =    0;
                normales[verticePuntero*3+1] =  1;
                normales[verticePuntero*3+2] =  0;
                texturaCoordenadas[verticePuntero*2] =      (float)j/((float)Vertice_Cuenta - 1);
                texturaCoordenadas[verticePuntero*2+1] =    (float)i/((float)Vertice_Cuenta - 1);
                verticePuntero++;
            }
        }
        int puntero = 0;
        for(int gz=0;gz<Vertice_Cuenta-1;gz++){
            for(int gx=0;gx<Vertice_Cuenta-1;gx++){
                int topIzquierda = (gz*Vertice_Cuenta)+gx;
                int topDerecha = topIzquierda + 1;
                int bottomIzquierda = ((gz+1)*Vertice_Cuenta)+gx;
                int bottomDerecha = bottomIzquierda + 1;
                indices[puntero++] = topIzquierda;
                indices[puntero++] = bottomIzquierda;
                indices[puntero++] = topDerecha;
                indices[puntero++] = topDerecha;
                indices[puntero++] = bottomIzquierda;
                indices[puntero++] = bottomDerecha;
            }
        }
        return loader.cargarToVAO(vertices, texturaCoordenadas, normales, indices);        
    }
    
}