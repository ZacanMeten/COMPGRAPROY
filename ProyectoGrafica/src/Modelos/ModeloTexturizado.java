/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Texturas.ModelTexture;

/**
 *
 * @author reant
 */
public class ModeloTexturizado {
    private ModeloRaw rawmodel;
    private ModelTexture texture;

    public ModeloTexturizado(ModeloRaw rawmodel, ModelTexture texture) {
        this.rawmodel = rawmodel;
        this.texture = texture;
    }

    public ModeloRaw getRawmodel() {
        return rawmodel;
    }

    public void setRawmodel(ModeloRaw rawmodel) {
        this.rawmodel = rawmodel;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public void setTexture(ModelTexture texture) {
        this.texture = texture;
    }
    
    
}
