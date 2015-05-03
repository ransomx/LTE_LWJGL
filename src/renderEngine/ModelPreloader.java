/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine;

import models.TexturedModel;
import textures.ModelTexture;

/**
 *
 * @author Dominik
 */
public class ModelPreloader {

    public static TexturedModel cube;
    public static TexturedModel sphere;
    public static TexturedModel dragon;
    
    public static ModelTexture terrainIce;
    

    private final Loader loader;

    public enum Model {

        CUBE, SPHERE, TERRAIN_ICE, DRAGON
    }

    public ModelPreloader(Loader loader) {
        this.loader = loader;
        cube = new TexturedModel(OBJLoader.loadObjModel("object/cube", loader), new ModelTexture(loader.loadTexture("object/cubeUV")));
        sphere = new TexturedModel(OBJLoader.loadObjModel("object/sphere", loader), new ModelTexture(loader.loadTexture("object/sphereUV")));
        terrainIce = new ModelTexture(loader.loadTexture("terrain/grass2"));
        dragon = new TexturedModel(OBJLoader.loadObjModel("object/dragon", loader), new ModelTexture(loader.loadTexture("object/rider")));
    }

    public Loader getLoader() {
        return loader;
    }
}
