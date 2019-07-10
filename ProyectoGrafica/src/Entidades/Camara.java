/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class Camara{
    private Vector3f posicion = new Vector3f(0,8,-10);
    private float pitch = 0;       //Inclinacion arriba abajo siendo 0 mirar hacia el frente y arriba -90
    private float yaw = 0;      //Derecha o Izquierda
    private float roll = 0;         //InclinacionZ derecha o izquierda
    private float Velocidad = 1.25f;
    
    public Camara(){
        
    }
    public Camara(Vector3f posicion){
        float limite = 5;
        if(posicion.y < limite)  posicion.y = limite;
        this.posicion = posicion;
    }
    
    public void Mover(){
        float AdelanteZ = (float) (Math.cos(Math.toRadians(yaw)) * Velocidad);
        float AdelanteX = (float) (Math.sin(Math.toRadians(yaw)) * Velocidad);
        float CostadoDerZ  = (float) (Math.cos(Math.toRadians(yaw+90)) * Velocidad);
        float CostadoIzqZ  = (float) (Math.sin(Math.toRadians(yaw+90)) * Velocidad);
        
        //Ir hacia adelante o atras
        if(Keyboard.isKeyDown( Keyboard.KEY_S )){
            if(EvaluarZPos())   posicion.z += AdelanteZ;
            if(EvaluarXPos())   posicion.x -= AdelanteX;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_W )){
            if(EvaluarZPos())   posicion.z -= AdelanteZ;
            if(EvaluarXPos())   posicion.x += AdelanteX;
        }
        
        //Ir hacia los costados
        if(Keyboard.isKeyDown( Keyboard.KEY_A)){
            if(EvaluarZPos())   posicion.z += CostadoDerZ;
            if(EvaluarXPos())   posicion.x -= CostadoIzqZ;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_D)){
            if(EvaluarZPos())   posicion.z -= CostadoDerZ;
            if(EvaluarXPos())   posicion.x += CostadoIzqZ;
        }
        
        //Saltar
        if(Keyboard.isKeyDown( Keyboard.KEY_SPACE)){
            if(posicion.y < 30)
            posicion.y += Velocidad;
        }
        if(Keyboard.isKeyDown( Keyboard.KEY_LCONTROL)){
            if(posicion.y > 5)
            posicion.y -= Velocidad;
        }
        
        //Mover punto de la vista
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
        
        System.out.println(posicion.toString());
        if(yaw>180)   yaw = -180;
        if(yaw<-180)    yaw = 180;
        MoverDentroMapa();
    }
    
    private void MoverDentroMapa(){
        if(posicion.x>0){
            if(!EvaluarXPos())
                posicion.x = 789;
        }else{
            if(!EvaluarXPos())
                posicion.x = -789;
        }
        
        if(posicion.z<-400){
            if(!EvaluarZPos()){
                posicion.z = -789;
            }
        }else{
            if(!EvaluarZPos())
                posicion.z = -2;            
        }
    }

    private boolean EvaluarZPos(){
        int Limite = 790;
        if((posicion.z < -1)&&(posicion.z > -Limite)){
            return true;
        }else{
            return false;
        }
        
    }
    private boolean EvaluarXPos(){
        int Limite = 790;
        if((posicion.x>-Limite)&&(posicion.x<Limite)){
            return true;
        }else{
            return false;
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
