/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine;

import terrains.Terrain;

/**
 *
 * @author Domík
 */
public class TerrainFactory{
    
    private final ModelPreloader modelPreloader;
    public TerrainFactory(ModelPreloader loader) {
        this.modelPreloader = loader;
    }
    
    public Terrain createObject(ModelPreloader.Model type) {
        Terrain ret = null;
        switch (type) {
            case TERRAIN_ICE:
                ret = new Terrain(-0.5f, -0.5f, modelPreloader.getLoader(), modelPreloader.terrainIce, "heightmap");
                break;
        }
        return ret;
    }
}
