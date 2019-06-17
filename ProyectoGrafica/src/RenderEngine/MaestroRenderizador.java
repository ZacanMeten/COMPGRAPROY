/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderEngine;

import Entidades.Camara;
import Entidades.Entidad;
import Entidades.Luz;
import Modelos.ModeloTexturizado;
import Sombreadores.StaticShader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author reant
 */
public class MaestroRenderizador {
    private StaticShader shader = new StaticShader();
    private Renderizador render = new Renderizador(shader);
    
    //Empaquedado de los objetos que tendran texturas y Entidad
    private Map<ModeloTexturizado,List<Entidad>> entidades = new HashMap<ModeloTexturizado, List<Entidad>>();
    
    public void renderizar(Luz sol, Camara camera){
        render.preparar();
        shader.empezar();
        shader.cargarLuz(sol);
        shader.cargarViewMatrix(camera);
        render.render(entidades);
        shader.parar();
        entidades.clear();
    }
    
    public void procesarEntidad(Entidad entidad){
        ModeloTexturizado modeloEntidad = entidad.getModelo();
        List<Entidad> lote = entidades.get(modeloEntidad);
        if(lote != null){
            lote.add(entidad);
        }else{
            List<Entidad> nuevoLote = new ArrayList<Entidad>();
            nuevoLote.add(entidad);
            entidades.put(modeloEntidad, nuevoLote);
        }
    }
    
    public void limpiar(){
        shader.limpiar();
    }
}
