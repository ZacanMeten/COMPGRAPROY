/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Modelos.ModeloTexturizado;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class Entidad {
    private ModeloTexturizado modelo;
    private Vector3f posicion;
    private float rotX, rotY, rotZ;
    private float escala;

    public Entidad(ModeloTexturizado modelo, Vector3f posicion, float rotX, float rotY, float rotZ, float escala) {
        this.modelo = modelo;
        this.posicion = posicion;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.escala = escala;
    }
    
    public void IncrementarPosicion(float dx, float dy, float dz){
        this.posicion.x += dx;
        this.posicion.y += dy;
        this.posicion.z += dz;
    }
    public void IncrementarRotacion(float rx, float ry, float rz){
        this.rotX += rx;
        this.rotY += ry;
        this.rotZ += rz;
    }

    public ModeloTexturizado getModelo() {
        return modelo;
    }

    public void setModelo(ModeloTexturizado modelo) {
        this.modelo = modelo;
    }

    public Vector3f getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector3f posicion) {
        this.posicion = posicion;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }
    
}
