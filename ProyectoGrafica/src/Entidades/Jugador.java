/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Modelos.ModeloTexturizado;
import RenderEngine.ManagerDisplay;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class Jugador extends Entidad{

    private static final float RUN_VELOCIDAD = 20;
    private static final float GIRO_VELOCIDAD = 120;
    
    private float actualVelocidad = 0;
    private float actualGiroVelocidad = 0;
    
    public Jugador(ModeloTexturizado modelo, Vector3f posicion, float rotX, float rotY, float rotZ, float escala) {
        super(modelo, posicion, rotX, rotY, rotZ, escala);
    }
    
    public void moverse(){
        verInputs();
        super.IncrementarRotacion(0, actualGiroVelocidad * ManagerDisplay.getTiempoFrameSegundos(), 0);
        float distancia = actualVelocidad * ManagerDisplay.getTiempoFrameSegundos();
        float dx = (float) (distancia * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distancia * Math.cos(Math.toRadians(super.getRotY())));
        super.IncrementarPosicion(dx,0,dz);
    }
    
    private void verInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            this.actualVelocidad = RUN_VELOCIDAD;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            this.actualVelocidad = -RUN_VELOCIDAD;
        }else{
            this.actualVelocidad = 0;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            this.actualGiroVelocidad = -GIRO_VELOCIDAD;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            this.actualGiroVelocidad = GIRO_VELOCIDAD;
        }else{
            this.actualGiroVelocidad = 0;
        }
    }
}
