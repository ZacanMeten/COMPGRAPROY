/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Entidades.Camara;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author reant
 */
public class Matematicas {
    public static Matrix4f crearTransformationMatrix(Vector3f traslacion, float rx, float ry, float rz, float escala ){
        Matrix4f matriz = new Matrix4f();
        matriz.setIdentity();
        Matrix4f.translate(traslacion, matriz, matriz);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matriz , matriz);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matriz , matriz);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matriz , matriz);
        Matrix4f.scale(new Vector3f(escala, escala, escala), matriz, matriz);
        return matriz;
    }
    
    public static Matrix4f crearMatrizVista(Camara camara){
        Matrix4f matriz  = new Matrix4f();
        matriz.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camara.getPitch()), new Vector3f(1, 0, 0), matriz, matriz);
        Matrix4f.rotate((float) Math.toRadians(camara.getYaw()), new Vector3f(0, 1, 0), matriz, matriz);
        Matrix4f.rotate((float) Math.toRadians(camara.getRoll()), new Vector3f(0, 0, 1), matriz, matriz);
        Vector3f camaraPos = camara.getPosicion();
        Vector3f negativeCameraPos = new Vector3f(-camaraPos.x,-camaraPos.y,-camaraPos.z);
        Matrix4f.translate(negativeCameraPos, matriz, matriz);
        return matriz;
    }
}
