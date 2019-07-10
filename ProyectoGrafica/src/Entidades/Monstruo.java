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
public class Monstruo extends Entidad{

    private static final float RUN_VELOCIDAD = 20;
    private static final float GIRO_VELOCIDAD = 120;
    
    private float actualVelocidad = 0;
    private float actualGiroVelocidad = 0;
    
    public Monstruo(ModeloTexturizado modelo, Vector3f posicion, float rotX, float rotY, float rotZ, float escala) {
        super(modelo, posicion, rotX, rotY, rotZ, escala);
    }
    
    public void seguir(Vector3f target){
        
    }
    
}
