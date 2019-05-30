/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Display;

/**
 *
 * @author reant
 */
public class ModeloRaw {
    private int vaoID;  //Numeracion de VAO
    private int verticeCount; //Enumeracion del vertice

    public ModeloRaw(int vaoID, int verticeCount) {
        this.vaoID = vaoID;
        this.verticeCount = verticeCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVerticeCount() {
        return verticeCount;
    }
    
}
