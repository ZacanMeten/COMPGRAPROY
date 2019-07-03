/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjConvertidor;

import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class Vertice {
    private static final int NO_INDICE = -1;
    
    private Vector3f posicion;
    private int texturaIndice = NO_INDICE;
    private int normalIndice = NO_INDICE;
    private Vertice dobleVertice = null;
    private int indice;
    private float longitud;

    public Vertice(int indice, Vector3f posicion){
        this.indice = indice;
        this.posicion = posicion;
        this.longitud = posicion.length();
    }
    
    public int getIndice() {
        return indice;
    }

    public float getLongitud() {
        return longitud;
    }
    
    public boolean isSet(){
        return texturaIndice!=NO_INDICE && normalIndice!=NO_INDICE;
    }
    
    public boolean hasMismaTexturaYNormal(int otroTexturaIndice, int otroNormalIndice){
        return otroTexturaIndice==texturaIndice && otroNormalIndice==normalIndice;
    }

    public void setTexturaIndice(int texturaIndice) {
        this.texturaIndice = texturaIndice;
    }

    public void setNormalIndice(int normalIndice) {
        this.normalIndice = normalIndice;
    }

    public Vector3f getPosicion() {
        return posicion;
    }

    public int getTexturaIndice() {
        return texturaIndice;
    }

    public int getNormalIndice() {
        return normalIndice;
    }

    public Vertice getDobleVertice() {
        return dobleVertice;
    }

    public void setDobleVertice(Vertice dobleVertice) {
        this.dobleVertice = dobleVertice;
    }
    
    
}
