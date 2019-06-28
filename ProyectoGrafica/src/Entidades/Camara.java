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
    private Vector3f posicion = new Vector3f(0,5,0);
    private float pitch = 1;       //Inclinacion arriba abajo
    private float yaw = 0;      //Derecha o Izquierda
    private float roll = 0;         //Inclinacion derecha o izquierda
    private float Velocidad = 1.2f;
    
    public Camara(){
        
    }
    public Camara(Vector3f posicion){
        if(posicion.y < 1)  posicion.y = 1;
        this.posicion = posicion;
    }
    public void Mover(){
        if(Keyboard.isKeyDown( Keyboard.KEY_S )){
            posicion.z += Velocidad; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_W )){
            posicion.z -= Velocidad; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_A )){
            posicion.x -= Velocidad; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_D )){
            posicion.x += Velocidad; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_SPACE)){
            posicion.y += Velocidad;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_LCONTROL)){
            if(posicion.y > 2)
            posicion.y -= Velocidad;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_NUMPAD8 )){
            pitch -= 0.7f;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_NUMPAD2 )){
            pitch += 0.7f;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_NUMPAD4)){
            yaw -= 0.8f;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_NUMPAD6)){
            yaw += 0.8f;
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
