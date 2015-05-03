/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine;

import models.TexturedModel;
import renderEngine.ModelPreloader.Model;
import textures.ModelTexture;

/**
 *
 * @author Domík
 */
public class ModelFactory {

    private ModelPreloader loader;

    public ModelFactory(ModelPreloader loader) {
        this.loader = loader;
    }

    public TexturedModel createModel(Model type) {
        TexturedModel ret = null;
        switch (type) {
            case CUBE:
                ret = new TexturedModel(loader.cube);
                break;
            case SPHERE:
                ret = new TexturedModel(loader.sphere);
                break;
        }
        return ret;
    }
}
