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
    private float pitch = 1;       //Inclinacion arriba abajo siendo 0 mirar hacia el frente y arriba -90
    private float yaw = 0;      //Derecha o Izquierda
    private float roll = 0;         //Inclinacion derecha o izquierda
    private float Velocidad = 1.25f;
    
    public Camara(){
        
    }
    public Camara(Vector3f posicion){
        float limite = 5;
        if(posicion.y < limite)  posicion.y = limite;
        this.posicion = posicion;
    }
    
    public void Mover(){
        float tamanoX = 780;
        if(Keyboard.isKeyDown( Keyboard.KEY_S )){
            if(posicion.z < -15)
            posicion.z += Velocidad; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_W )){
            if(posicion.z > -780)
            posicion.z -= Velocidad; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_A )){
            if(posicion.x>-tamanoX)
            posicion.x -= Velocidad; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_D )){
            if(posicion.x<tamanoX)
            posicion.x += Velocidad; 
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_SPACE)){
            if(posicion.y < 30)
            posicion.y += Velocidad;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_LCONTROL)){
            if(posicion.y > 2)
            posicion.y -= Velocidad;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_NUMPAD8 )){
            if(pitch>-90)
            pitch -= 0.7f;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_NUMPAD2 )){
            if(pitch<50)
            pitch += 0.7f;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_NUMPAD4)){
            yaw -= 0.8f;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_NUMPAD6)){
            yaw += 0.8f;
        }
        
        if((yaw>360)||(yaw<-360))   yaw = 0;
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
