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

    private boolean hasTransparencia = false;
    private boolean usarLuzFalsa = false;
    
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

    public boolean isHasTransparencia() {
        return hasTransparencia;
    }

    public void setHasTransparencia(boolean hasTransparencia) {
        this.hasTransparencia = hasTransparencia;
    }

    public boolean isUsarLuzFalsa() {
        return usarLuzFalsa;
    }

    public void setUsarLuzFalsa(boolean usarLuzFalsa) {
        this.usarLuzFalsa = usarLuzFalsa;
    }
    
    
}
