/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class Camara {
    private Vector3f posicion = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;      //Derecha o Izquierda
    private float roll;         //Inclinacion hacia el exterior
    
    public Camara(){
        
    }
    
    public void Mover(){
        if(Keyboard.isKeyDown( Keyboard.KEY_S )){
            posicion.z -= 0.02f; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_W )){
            posicion.z += 0.02f; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_A )){
            posicion.x -= 0.02f; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_D )){
            posicion.x += 0.02f; 
        }
    }

    public Vector3f getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector3f posicion) {
        this.posicion = posicion;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
    
}
