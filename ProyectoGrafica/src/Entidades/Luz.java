/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class Luz {
    private Vector3f posicion;
    private Vector3f color;

    public Luz(Vector3f posicion, Vector3f color) {
        this.posicion = posicion;
        this.color = color;
    }

    public Vector3f getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector3f Pos){
        this.posicion = new Vector3f(Pos.x,Pos.y+10,Pos.z+1000);
        System.out.println("Posicion de la Luz: "+this.posicion.toString());
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }
    
    
    
}
