/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjConvertidor;

/**
 *
 * @author reant
 */
public class ModeloData {
    private float[] vertices;
    private float[] texturaCordenadas;
    private float[] normales;
    private int[] indices;
    private float LejanoPunto;

    public ModeloData(float[] vertices, float[] texturaCordenadas, float[] normales, int[] indices, float LejanoPunto) {
        this.vertices = vertices;
        this.texturaCordenadas = texturaCordenadas;
        this.normales = normales;
        this.indices = indices;
        this.LejanoPunto = LejanoPunto;
    }

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public float[] getTexturaCordenadas() {
        return texturaCordenadas;
    }

    public void setTexturaCordenadas(float[] texturaCordenadas) {
        this.texturaCordenadas = texturaCordenadas;
    }

    public float[] getNormales() {
        return normales;
    }

    public void setNormales(float[] normales) {
        this.normales = normales;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public float getLejanoPunto() {
        return LejanoPunto;
    }

    public void setLejanoPunto(float LejanoPunto) {
        this.LejanoPunto = LejanoPunto;
    }
    
}
