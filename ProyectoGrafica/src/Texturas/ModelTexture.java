/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Texturas;

/**
 *
 * @author reant
 */
public class ModelTexture {
    private int texturaID;
    private float brilloDamper;
    private float reflectividad;

    public ModelTexture(int texturaID) {
        this.texturaID = texturaID;
    }

    public int getTexturaID() {
        return texturaID;
    }

    public float getBrilloDamper() {
        return brilloDamper;
    }

    public void setBrilloDamper(float brilloDamper) {
        this.brilloDamper = brilloDamper;
    }

    public float getReflectividad() {
        return reflectividad;
    }

    public void setReflectividad(float reflectividad) {
        this.reflectividad = reflectividad;
    }
    
}
