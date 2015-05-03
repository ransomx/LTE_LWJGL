/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.ModelPreloader;

/**
 *
 * @author Dominik
 */
public class EntityFactory {

    private final ModelPreloader loader;

    public EntityFactory(ModelPreloader loader) {
        this.loader = loader;
    }

    public EntityObject createEntityObject(TexturedModel staticModel, Vector3f vector) {
        EntityObject entity = new EntityObject(staticModel, vector, 0, 0, 0, 1);
        entity.getModel().getTexture().setShineDamper(0.8f);
        entity.getModel().getTexture().setReflectivity(1f);
        return entity;
    }

    public LightSource createLightSource(Light light, Vector3f vector) {
        light.setPosition(vector);
        LightSource entity = new LightSource(ModelPreloader.sphere, vector, light);
        return entity;
    }

    public Light createLight(Vector3f pos, Vector3f col, Vector3f attenuation) {
        Light newLight;
        if (attenuation != null) {
            newLight = new Light(pos, col, attenuation);
        }else
            newLight = new Light(pos, col);
        return newLight;
    }
}
